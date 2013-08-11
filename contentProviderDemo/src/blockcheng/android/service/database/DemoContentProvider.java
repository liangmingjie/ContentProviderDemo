package blockcheng.android.service.database;

import java.util.HashMap;

import blockcheng.android.model.AdvertisementEntity;
import blockcheng.android.model.BasicEntity;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class DemoContentProvider extends ContentProvider {
	private SQLiteOpenHelper dbHelper;
	private static final UriMatcher URI_MATCHER;
	static {//TODO 需要整合下面的地址：
		URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
		URI_MATCHER.addURI(DBConfig.AUTHORITY, SearchManager.SUGGEST_URI_PATH_QUERY,
				DBConfig.sSEARCH);
		URI_MATCHER.addURI(DBConfig.AUTHORITY, SearchManager.SUGGEST_URI_PATH_QUERY
				+ "/*", DBConfig.sSEARCH);
		
		//advertisement uri config
		URI_MATCHER.addURI(DBConfig.AUTHORITY, AdvertisementEntity.sTABLE_NAME, DBConfig.sADVERTISEMENT);
		URI_MATCHER.addURI(DBConfig.AUTHORITY, AdvertisementEntity.sTABLE_NAME+"/*", DBConfig.sADVERTISEMENT_ID);
		
	}

	// TODO: need more investigate.
	private static final HashMap<String, String> SUGGESTION_PROJECTION_MAP;
	static {
		SUGGESTION_PROJECTION_MAP = new HashMap<String, String>();
		SUGGESTION_PROJECTION_MAP.put(SearchManager.SUGGEST_COLUMN_TEXT_1,
				"topicName " + " AS " + SearchManager.SUGGEST_COLUMN_TEXT_1);
		SUGGESTION_PROJECTION_MAP.put(SearchManager.SUGGEST_COLUMN_TEXT_2,
				"goodName " + " AS " + SearchManager.SUGGEST_COLUMN_TEXT_2);
		SUGGESTION_PROJECTION_MAP.put(
				SearchManager.SUGGEST_COLUMN_INTENT_DATA_ID, AdvertisementEntity._ID + " AS "
						+ SearchManager.SUGGEST_COLUMN_INTENT_DATA_ID);
		SUGGESTION_PROJECTION_MAP.put(AdvertisementEntity._ID, AdvertisementEntity._ID);
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		dbHelper = new DBHelper(getContext());
		return true;
	}

	// TODO: update the manifest accordingly.
	public String getType(Uri uri) {
		switch (URI_MATCHER.match(uri)) {
		case DBConfig.sADVERTISEMENT:
		case DBConfig.sADVERTISEMENT_ID:
			return "vnd.android.cursor.dir/" + DBConfig.AUTHORITY + "."
					+ AdvertisementEntity.sTABLE_NAME;
		
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues initialValues) {
		// TODO Auto-generated method stub
		Log.i(DBConfig.DB_TAG, "insert::"+uri.toString());
		ContentValues values;

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		switch(URI_MATCHER.match(uri)){
		case DBConfig.sADVERTISEMENT:
			if (initialValues != null) {
				values = new ContentValues(initialValues);
				values.put(BasicEntity.sKEY_LASTUPDATETIMEADID, System.currentTimeMillis());
			} else {
				values = new ContentValues();
			}
			final long rowId = db.insert(AdvertisementEntity.sTABLE_NAME, AdvertisementEntity.sKEY_ADPIC, values);
			if (rowId > 0) {
				Uri insertUri = ContentUris.withAppendedId(DBConfig.CONTENT_URI_AdvertisementEntity, rowId);
				getContext().getContentResolver().notifyChange(uri, null);
				return insertUri;
			}
			throw new SQLException("Failed to insert row into " + uri);
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
			
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		Log.i(DBConfig.DB_TAG, "delete::"+uri.toString());
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int count;
		switch (URI_MATCHER.match(uri)) {
		case DBConfig.sADVERTISEMENT:
			count = db.delete(AdvertisementEntity.sTABLE_NAME, selection, selectionArgs);
			break;
		case DBConfig.sADVERTISEMENT_ID:
			String adId = uri.getPathSegments().get(1);
			count = db.delete(AdvertisementEntity.sTABLE_NAME,
					AdvertisementEntity.sKEY_ADID
							+ "="
							+ adId
							+ (!TextUtils.isEmpty(selection) ? " AND ("
									+ selection + ')' : ""), selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("   Unknown URI " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}
	

	@Override
	public int update(Uri uri, ContentValues values, String where,
			String[] whereArgs) {
		Log.i(DBConfig.DB_TAG, "update::"+uri.toString() +": value="+values.toString());
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int count = -1;
		switch (URI_MATCHER.match(uri)) {
		case DBConfig.sADVERTISEMENT_ID:
			Log.i(DBConfig.DB_TAG, "update::sADVERTISEMENT_ID");
			String adverstisementId = uri.getPathSegments().get(1);
			count = db.update(
					AdvertisementEntity.sTABLE_NAME,
					values,
					AdvertisementEntity.sKEY_ADID
							+ "='"
							+ adverstisementId
							+ "'"
							+ (!TextUtils.isEmpty(where) ? " AND (" + where
									+ ')' : ""), whereArgs);
			break;
		case DBConfig.sADVERTISEMENT:
			Log.i(DBConfig.DB_TAG, "update::sADVERTISEMENT");
			String aidString = values.getAsString(AdvertisementEntity.sKEY_ADID);
			values.remove(AdvertisementEntity.sKEY_ADID);
			count = db.update(
					AdvertisementEntity.sTABLE_NAME,
					values,
					AdvertisementEntity.sKEY_ADID
					+ "='"
					+ aidString
					+ "'"
					+ (!TextUtils.isEmpty(where) ? " AND (" + where
							+ ')' : ""), whereArgs);
			break;
		
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		return count;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Log.i(DBConfig.DB_TAG, "query::"+uri.toString());
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		String orderBy;
		switch (URI_MATCHER.match(uri)) {
		case DBConfig.sADVERTISEMENT:
			qb.setTables(AdvertisementEntity.sTABLE_NAME);
			break;
		case DBConfig.sADVERTISEMENT_ID:
			qb.setTables(AdvertisementEntity.sTABLE_NAME);
			qb.appendWhere(AdvertisementEntity.sKEY_ADID + "='"
					+ uri.getPathSegments().get(1) +"'");
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		// If no sort order is specified use the default
		if (TextUtils.isEmpty(sortOrder)) {
			orderBy = BasicEntity.sDEFAULTSORT;
		} else {
			orderBy = sortOrder;
		}
		
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor c = qb.query(db, projection, selection, selectionArgs, null,
				null, orderBy);
		c.setNotificationUri(getContext().getContentResolver(), uri);

		return c;
	}

	

}
