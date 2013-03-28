package ch.comem.vues.list;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
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
import ch.comem.vues.AddNewMission;

public class ListeGroupe extends Activity implements RestCallback{

	private ListView listGroups;
	 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupes);
        setupActionBar();
        
        SharedPreferences settings = getSharedPreferences("ChallengeMe", 0);
        AppPrefs appPrefs = new AppPrefs(ListeGroupe.this);
		int idUser = appPrefs.getUser_id();
		
		Connexion membreGet = new Connexion(ListeGroupe.this, "listgroupes/"+idUser, REQUEST_METHODE.GET);
		membreGet.execute();
		
		listGroups = (ListView) findViewById(R.id.listMyGroups);
 
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
				JSONArray listGroupe = new JSONArray(data);
				Log.i("array", listGroupe.toString());
				
				List<String> group = new ArrayList<String>();
		        for(int i=0;i<listGroupe.length();i++){
		        	JSONObject groupeCourant = listGroupe.getJSONObject(i);		        	
		        	group.add(groupeCourant.getString("nom"));
		        }
		        
		        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, group);
		        listGroups.setAdapter(adapter);
	        
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			listGroups.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> adapterView, 
					View view, 
		    	    int position,
		    	    long id) {
						Intent newMission = new Intent(ListeGroupe.this, AddNewMission.class);
						startActivity(newMission);
		    	  }
		    });
			
		}
		else if(status == 400){
			ToastCours msg = new ToastCours(data, ListeGroupe.this);
			ListeGroupe.this.finish();
		}
		
	}

}