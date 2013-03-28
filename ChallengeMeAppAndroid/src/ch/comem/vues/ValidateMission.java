package ch.comem.vues;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import ch.comem.challengeMe.R;
import ch.comem.connexion.AppPrefs;
import ch.comem.connexion.Connexion;
import ch.comem.connexion.Connexion.REQUEST_METHODE;
import ch.comem.connexion.RestCallback;
import ch.comem.utilitaire.ToastCours;

public class ValidateMission extends Activity implements RestCallback {
	
	private ListView listMissions;
	private String[] namesMission;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_validate_mission);
		// Show the Up button in the action bar.
		setupActionBar();
		
		//Context context = getApplicationContext();
		AppPrefs appPrefs = new AppPrefs(ValidateMission.this);
		int idUser = appPrefs.getUser_id();
		
		Connexion membreGet = new Connexion(ValidateMission.this, "listmissionsavalider/"+idUser, REQUEST_METHODE.GET);
		membreGet.execute();
		
		listMissions = (ListView) findViewById(R.id.listMissionsToValidate);

		
	    listMissions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	    	  @Override
	    	  public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
	  				int idMissionAValider = 3;
	  				Connexion connexion = new Connexion(ValidateMission.this, "missions/"+idMissionAValider+"?statut=REUSSIE", REQUEST_METHODE.PUT);
	  				connexion.execute();
	    	  }
	    	});
	    
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void processResult(int status, String data) {
		if(status == 200){
			try {
				// Liste des membres
				JSONArray listMission = new JSONArray(data);
				namesMission = new String[listMission.length()];
				// position membre
				for(int i=0;i<listMission.length();i++){
		        	JSONObject missionCourant = listMission.getJSONObject(i);
		        	namesMission[i] = missionCourant.getString("titre");
		        }
		        listMissions.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, namesMission));
		       
		        
	        
			} catch (JSONException e) {
				e.printStackTrace();
			}}
		else if(status == 400){
			ToastCours msg = new ToastCours(data, ValidateMission.this);
			ValidateMission.this.finish();
		}
	
	}

}
