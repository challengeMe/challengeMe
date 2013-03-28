package ch.comem.vues;

import java.sql.Array;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import ch.comem.challengeMe.R;
import ch.comem.connexion.AppPrefs;
import ch.comem.connexion.Connexion;
import ch.comem.connexion.Connexion.REQUEST_METHODE;
import ch.comem.connexion.RestCallback;
import ch.comem.utilitaire.ToastCours;

public class AssignNewMission extends Activity implements RestCallback {

	private JSONObject json_mission;
	private Button bAddNewMission;
	private ListView listMembers;
	private JSONObject[] memberList;
	private ArrayAdapter<String> membreAdapter;
	public int posIdMembre [];
	
	private int membreEffectue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_assign_new_mission);
		// Show the Up button in the action bar.
		setupActionBar();

		AppPrefs appPrefs = new AppPrefs(AssignNewMission.this);
		final int idUser = appPrefs.getUser_id();

		
		// Afficher le profil du User
		int idGroupe = 2;
		Connexion membreGet = new Connexion(AssignNewMission.this, "listmembres/"+idGroupe+"?idMembre="+idUser, REQUEST_METHODE.GET);
		membreGet.execute();
		
		Intent newMission = getIntent();
		try {
			json_mission = new JSONObject(newMission.getStringExtra("NewMission"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		// Identifier les views
		bAddNewMission = (Button) findViewById(R.id.addNewMission);
		listMembers = (ListView) findViewById(R.id.listMembres);
		
		
		bAddNewMission.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Connexion connexion = new Connexion(AssignNewMission.this, 
						"missions?idMembreValide="+idUser+"&idMembreEffectue="+membreEffectue, REQUEST_METHODE.POST);
				connexion.execute(json_mission);
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
				JSONArray listMembres = new JSONArray(data);
				memberList = new JSONObject[listMembres.length()];
				String[] membreNames = new String[listMembres.length()]; 
				// position membre
				this.posIdMembre = new int [listMembres.length()];
		        for(int i=0;i<listMembres.length();i++){		        	 
		        	JSONObject membreCourant = listMembres.getJSONObject(i);
		        	memberList[i] = membreCourant;
		        	membreNames[i] = membreCourant.getString("nom") + " " + membreCourant.getString("prenom");
		        }
				membreAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, membreNames);
				
		        listMembers.setAdapter(membreAdapter);
		        
	        
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			listMembers.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView arg0, View arg1,
						int arg2, long arg3) {
					try {
						membreEffectue = memberList[arg2].getInt("id");
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});
		}
		else if(status == 400){
			ToastCours msg = new ToastCours(data, AssignNewMission.this);
			AssignNewMission.this.finish();
		}
	}

}
