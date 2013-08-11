package cn.helloclq.android.activity;

import blockcheng.android.R;
import blockcheng.android.model.AdvertisementEntity;
import blockcheng.android.service.database.DBManager;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class TestDBAct extends Activity {

	EditText etCondition;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dbtest_activity);
		etCondition = (EditText)findViewById(R.id.et_condition);
		
	}
	
	
	public void handleEvent(View view){

		String adIdString = etCondition.getText().toString();
		AdvertisementEntity aEntity = new AdvertisementEntity();
		aEntity.setEntityId(adIdString);
		aEntity.setEntityName("ad"+adIdString);
		aEntity.setAdType(Integer.parseInt(adIdString));
		aEntity.setAdTarget(adIdString);
		
		
		//public WebTaskTest(Context context, boolean showDialog,
//		boolean cancelAble, String dialogLoadingStr,
//		WebRequestCallbackInfc cb)
		switch(view.getId()) {
		case R.id.db_button1://add 
			DBManager.getInstance().addAdvertiseMent(getContentResolver(), aEntity);
			break;
		case R.id.db_button2://delete
			DBManager.getInstance().deleteAdvertiseMentById(getContentResolver(), adIdString);
			
			break;
		case R.id.db_button3://update
			aEntity.setEntityName("ad update"+adIdString);
			DBManager.getInstance().updateAdervertiseEntity(getContentResolver(), aEntity);
			break;
		case R.id.db_button4://query
			DBManager.getInstance().querAllAdvertisementEntity(getContentResolver());
			break;
		
		}
		
		
		
	}
	
	
}
