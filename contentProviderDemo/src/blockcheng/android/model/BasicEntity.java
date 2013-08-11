package blockcheng.android.model;

import java.io.Serializable;

import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcelable;
import android.provider.BaseColumns;

public abstract class BasicEntity implements BaseColumns,Serializable, Parcelable{
	private static final long serialVersionUID = 7169909425485915669L;
	
	protected String entityName;
	protected String entityId;
	protected long lastUpdateTime;
	
	public static final String sDEFAULTSORT = "  lastUpdateTime desc";
	public static final String sKEY_LASTUPDATETIMEADID = "lastUpdateTime";
	/**
	 * demo methods
	 * @param obj
	 * @return
	 */
	public static BasicEntity translateJson2Object(JSONObject obj){
		//TODO:this method must be override by subclass.
		return null;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	
	public abstract ContentValues getContentValues();
	
	/**
	 * demo method ：should be copy by subclass
	 * @param c
	 * @return
	 */
	public static BasicEntity fromCursor(Cursor c) {
		return null;
	}

	@Override
	public String toString() {
		return "id=" + entityId  + ", name="
				+ entityName + ", lastUpdateTime=" + lastUpdateTime + "";
	}
}