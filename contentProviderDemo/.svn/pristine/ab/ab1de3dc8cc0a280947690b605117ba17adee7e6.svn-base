package blockcheng.android.service.database;

import java.util.ArrayList;

import blockcheng.android.model.AdvertisementEntity;
import blockcheng.android.model.BasicEntity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

/**
 * database colleague
 * @author Block Cheng
 *
 */
public class DBManager {

	private static DBManager instance = new DBManager();
	public static DBManager getInstance() {
		return instance;
	}
	protected DBManager() {
		super();
	}
	
	private static String[] sArguments1 = new String[1];
	
	
	public boolean addAdvertiseMent(ContentResolver resolver,AdvertisementEntity ae) {
		if (querAdvertisementEntity(resolver, ae.getEntityId()) != null) {
			updateAdervertiseEntity(resolver, ae);
			return true;
		}
		
		if (ae != null) {
			final Uri uri = resolver.insert(DBConfig.CONTENT_URI_AdvertisementEntity,
					ae.getContentValues());
			if (uri != null) {
				Log.i(DBConfig.DB_TAG, "addAdvertiseMent::"+ae);
				return true;
			}
		}
		return false;
	}  
	
	public boolean deleteAdvertiseMentById(ContentResolver contentResolver,
			String adId){
		int count = contentResolver.delete(
				Uri.parse("content://"+DBConfig.AUTHORITY +"/"+AdvertisementEntity.sTABLE_NAME+"/" + adId),
				null,
				null);
		return count > 0;
	}
	
	public boolean updateAdervertiseEntity(ContentResolver contentResolver,
			AdvertisementEntity aEntity) {
		 ContentValues values = aEntity.getContentValues();
	     values.put(BasicEntity.sKEY_LASTUPDATETIMEADID, System.currentTimeMillis());
			int res = contentResolver.update(
					Uri.parse("content://"+DBConfig.AUTHORITY +"/"+AdvertisementEntity.sTABLE_NAME),
					values, null, null);
			if (res > 0)
			{
				contentResolver.notifyChange(DBConfig.CONTENT_URI_AdvertisementEntity, null);
				return true;
			}else
			{
				return false;
			}
		
	}
	
	
	public AdvertisementEntity querAdvertisementEntity(ContentResolver resolver,String adId) {
		AdvertisementEntity ae = null;
		Cursor c = null;
		try {
			sArguments1[0] = adId;
			c = resolver.query(
					Uri.parse("content://"+DBConfig.AUTHORITY +"/"+AdvertisementEntity.sTABLE_NAME+"/" + adId),
					AdvertisementEntity.ADS_PROJECTION_ALL, null, null, null);
			if (c.getCount() > 0) {
				if (c.moveToFirst()) {
					ae = AdvertisementEntity.fromCursor(c);
				}
			}
		} finally {
			if (c != null)
				c.close();
		}

		return ae;
	}
	
	public ArrayList<AdvertisementEntity> querAllAdvertisementEntity(ContentResolver resolver) {
		ArrayList<AdvertisementEntity> aes = new ArrayList<AdvertisementEntity>();
		Cursor c = null;
		AdvertisementEntity ae  = null;
		try {
			c = resolver.query(
					Uri.parse("content://"+DBConfig.AUTHORITY +"/"+AdvertisementEntity.sTABLE_NAME),
					AdvertisementEntity.ADS_PROJECTION_ALL, null, null, null);
			if (c.getCount() > 0) {				
				if (c.moveToFirst()) {
					ae = AdvertisementEntity.fromCursor(c);
					Log.i(DBConfig.DB_TAG, "ad:"+ae);
					aes.add(ae);
				}				
				while(!c.isLast()){
					c.moveToNext();
					ae = AdvertisementEntity.fromCursor(c);
					Log.i(DBConfig.DB_TAG, "ad:"+ae);
					aes.add(ae);
				}
			}
		} finally {
			if (c != null)
				c.close();
		}

		return aes;
	}
	



}
