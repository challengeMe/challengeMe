package ch.comem.vues.list;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import ch.comem.challengeMe.R;
import ch.comem.connexion.AppPrefs;
import ch.comem.connexion.Connexion;
import ch.comem.connexion.Connexion.REQUEST_METHODE;
import ch.comem.connexion.RestCallback;
import ch.comem.utilitaire.ToastCours;

public class ListeMission extends Activity implements RestCallback{

	private ListView maListViewPerso;
	 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_main);
        setupActionBar();
        
        SharedPreferences settings = getSharedPreferences("ChallengeMe", 0);
        AppPrefs appPrefs = new AppPrefs(ListeMission.this);
		int idUser = appPrefs.getUser_id();
		Connexion membreGet = new Connexion(ListeMission.this, "listmissions/"+idUser, REQUEST_METHODE.GET);
		membreGet.execute();
         
        //Recuperation de la listview creee dans le fichier main.xml
        maListViewPerso = (ListView) findViewById(R.id.lvGBM);

        //Enfin on met un ecouteur d'evnement sur notre listView
        maListViewPerso.setOnItemClickListener(new OnItemClickListener() {
			@Override
        	@SuppressWarnings("unchecked")
         	public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				//on recupre la HashMap contenant les infos de notre item (titre, description, img)
        		HashMap<String, String> map = (HashMap<String, String>) maListViewPerso.getItemAtPosition(position);
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.liste_badge, menu);
		return true;
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
		Log.i("test", data);
		if(status == 200){
			try {
				JSONArray listMission = new JSONArray(data);	
				ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
		        for(int i=0;i<listMission.length();i++){
		        	JSONObject missionCourant = listMission.getJSONObject(i);
			        HashMap<String, String> map = new HashMap<String, String>();

			        map.put("img", String.valueOf(R.drawable.iconmission));
			        map.put("nom", missionCourant.getString("titre"));
			        listItem.add(map);
		        }
		        
		        SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.affichageitem,
		               new String[] {"img", "nom"}, new int[] {R.id.imgBadge,R.id.titreBadge});
		 
		        maListViewPerso.setAdapter(mSchedule);
	        
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else if(status == 400){
			ToastCours msg = new ToastCours(data, ListeMission.this);
			ListeMission.this.finish();
		}
		
	}

}
