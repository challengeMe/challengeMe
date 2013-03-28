package ch.comem.vues.list;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
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

public class ListeBadge extends Activity implements RestCallback{

	private ListView maListViewPerso;
	 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_main);
        setupActionBar();
        
        SharedPreferences settings = getSharedPreferences("ChallengeMe", 0);
		// recup dans le cache
		AppPrefs appPrefs = new AppPrefs(ListeBadge.this);
		int idUser = appPrefs.getUser_id();

		
		// Afficher le profil du User
		Connexion membreGet = new Connexion(ListeBadge.this, "membres/"+idUser, REQUEST_METHODE.GET);
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
				JSONObject membre;
				membre = new JSONObject(data);
	
				ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
		        JSONArray listBadges = membre.getJSONArray("badges");
		        for(int i=0;i<listBadges.length();i++){
		        	JSONObject badgeCourant = listBadges.getJSONObject(i);
			        HashMap<String, String> map = new HashMap<String, String>();
			        map.put("titre", badgeCourant.getString("title"));
			        map.put("description", badgeCourant.getString("description"));
			        String badgeNom = badgeCourant.getString("icon");
			        // ajouter les badges
			        if(badgeNom.equals("badgecool")){
			        	map.put("img", String.valueOf(R.drawable.badgecool));
			        }
			        else if (badgeNom.equals("badgegeek")){
			        	map.put("img", String.valueOf(R.drawable.badgegeek));
			        }
			        else if (badgeNom.equals("badgenewbie")){
			        	map.put("img", String.valueOf(R.drawable.badgenewbie));
			        }
			        else if (badgeNom.equals("badgetrans")){
			        	map.put("img", String.valueOf(R.drawable.badgetrans));
			        }
			        listItem.add(map);
		        }
		        
		        SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.affichageitem,
		               new String[] {"img", "titre", "description"}, new int[] {R.id.imgBadge, R.id.titreBadge, R.id.descriptionBadge});
		 
		        maListViewPerso.setAdapter(mSchedule);
	        
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else if(status == 400){
			ToastCours msg = new ToastCours(data, ListeBadge.this);
		}
		
	}

}
