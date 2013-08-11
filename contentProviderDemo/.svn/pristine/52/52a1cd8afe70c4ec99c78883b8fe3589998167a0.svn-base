package blockcheng.android.service.database;

import blockcheng.android.model.AdvertisementEntity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper implements DBConfig{
	public DBHelper(Context context) {
		super(context, DBConfig.DATABASE_NAME, null, DBConfig.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		StringBuffer createAdSql = new StringBuffer("CREATE TABLE'");
		createAdSql.append(AdvertisementEntity.sTABLE_NAME);
		createAdSql.append( "'('adId'  TEXT NOT NULL," );
		createAdSql.append( "'adName'  TEXT,");
		createAdSql.append("'lastUpdateTime'  real DEFAULT 0,");
		createAdSql.append("'adType'  INTEGER,");
		
		createAdSql.append("'adPic'  TEXT,");
		createAdSql.append( "'adTarget'  TEXT,");
		createAdSql.append( "PRIMARY KEY ('adId')" );
		createAdSql.append( ");");;
		
		Log.i(DBConfig.DB_TAG, "createAdSql::"+createAdSql.toString());
		db.execSQL(createAdSql.toString());
		
		String createIndexSql_0 = "CREATE INDEX 'id' ON '" +
				AdvertisementEntity.sTABLE_NAME +
				"' ('adId' ASC);";
		db.execSQL(createIndexSql_0);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w(DBConfig.DB_TAG, "Upgrading database from version " + oldVersion
				+ " to " + newVersion + ", which will destroy all old data");

		db.execSQL("DROP TABLE IF EXISTS " +
				AdvertisementEntity.sTABLE_NAME);
		onCreate(db);
	}

}
