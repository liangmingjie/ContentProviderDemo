package blockcheng.android.model;

import java.io.Serializable;

import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;

public class AdvertisementEntity extends BasicEntity implements Serializable{
	private static final long serialVersionUID = 3045767248752772598L;
	
	/**
	 * 广告图片路径
	 **/
	private String adPic;
	/**
	 * 广告类型  1主题 2 单个商品（后可扩充，客户端可根据广告类型设定广告的点击状态）
	 **/
	private int adType;
	/**
	 * 广告对象地址（主题ID、单个商品ID或者其他）
	 **/
	private String adTarget;

	//'广告位置：1.banner（后扩充位置）
	private int adPosition;
	private String data;

	/**
	 * demo methods
	 * @param obj
	 * @return
	 */
	public static AdvertisementEntity translateJson2Object(JSONObject obj){
		//TODO:implement it later.
		AdvertisementEntity aEntity = new AdvertisementEntity();
		return aEntity;
	}
	public String getAdPic() {
		return adPic;
	}

	public void setAdPic(String adPic) {
		this.adPic = adPic;
	}

	public int getAdType() {
		return adType;
	}

	public void setAdType(int adType) {
		this.adType = adType;
	}

	public String getAdTarget() {
		return adTarget;
	}

	public void setAdTarget(String adTarget) {
		this.adTarget = adTarget;
	}

	public int getAdPosition() {
		return adPosition;
	}

	public void setAdPosition(int adPosition) {
		this.adPosition = adPosition;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	/******************************************************************/
	/*********************Database config information********************/
	/******************************************************************/
	
	public static final String sTABLE_NAME = "AdvertisementEntity";
	public static final String sKEY_ADID = "adId";
	public static final String sKEY_ADNAME = "adName";
	
	public static final String sKEY_ADTYPE = "adType";
	public static final String sKEY_ADPIC = "adPic";
	public static final String sKEY_ADTARGET = "adTarget";
	
	/*
	 * 要查询的全部字段
	 */
	public  static String[] ADS_PROJECTION_ALL = new String[] {
		"adId","adName","lastUpdateTime",
		"adType","adPic","adTarget",
	};

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
	}

	@Override
	public ContentValues getContentValues() {
		// TODO Auto-generated method stub
		final ContentValues values = new ContentValues();
		values.put(sKEY_ADID, getEntityId());
		values.put(sKEY_ADNAME, getEntityName());
		values.put(sKEY_ADTYPE, adType);
		
		values.put(sKEY_ADPIC, adPic);
		values.put(sKEY_ADTARGET, adTarget);
		return values;
	}

	public static AdvertisementEntity fromCursor(Cursor c) {
		AdvertisementEntity aEntity = new AdvertisementEntity();
		
		aEntity.entityId = c.getString(c.getColumnIndexOrThrow(sKEY_ADID));
		aEntity.entityName = c.getString(c.getColumnIndexOrThrow(sKEY_ADNAME));
		aEntity.adPic = c.getString(c.getColumnIndexOrThrow(sKEY_ADTARGET));
		aEntity.adType = c.getInt(c.getColumnIndexOrThrow(sKEY_ADTYPE));
		aEntity.lastUpdateTime = c.getLong(c.getColumnIndexOrThrow(BasicEntity.sKEY_LASTUPDATETIMEADID));
		return aEntity;
	}

	@Override
	public String toString() {
		return "AdvertisementEntity [" + super.toString()+ "adPic= "+ adPic + ", adType=" + adType
				+ ", adTarget=" + adTarget + ", adPosition=" + adPosition
				+ ", data=" + data + "]" + super.toString();
	}
}
