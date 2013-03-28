package ch.comem.vues;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import ch.comem.challengeMe.R;
import ch.comem.connexion.AppPrefs;
import ch.comem.connexion.Connexion;
import ch.comem.connexion.Connexion.REQUEST_METHODE;
import ch.comem.connexion.RestCallback;
import ch.comem.utilitaire.ToastCours;
import ch.comem.vues.list.ListeBadge;
import ch.comem.vues.list.ListeGroupe;
import ch.comem.vues.list.ListeMission;

public class ProfilUser extends Activity implements OnClickListener, RestCallback   {
	
	Button bListeBadges;
	private Button bValidateMissions;
	
	private Button bMissions;
	private Button bGroupes;
	private Button bBadges;
	private TextView nomPrenom;
	private TextView points;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profil_user);
		// Show the Up button in the action bar.
		setupActionBar();
		
		// Identifier les views
		bMissions = (Button) findViewById(R.id.missions);
		bGroupes = (Button) findViewById(R.id.groupes);
		bBadges = (Button) findViewById(R.id.badges);
		nomPrenom = (TextView) findViewById(R.id.profilNomPrenom);
		points = (TextView) findViewById(R.id.profilPts);
		bValidateMissions = (Button) findViewById(R.id.validateMissions);

		bValidateMissions.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent validateMission = new Intent(ProfilUser.this, ValidateMission.class);
						startActivity(validateMission);			
					}
				});
		
		// recup dans le cache
		Context context = getApplicationContext();
		AppPrefs appPrefs = new AppPrefs(context);
		int idUser = appPrefs.getUser_id();
		
		// Afficher le profil du User
		Connexion profileGetter = new Connexion(ProfilUser.this, "membres/"+idUser, REQUEST_METHODE.GET);
		profileGetter.execute();
						
		// Ouvrir l'intent mes badges
		bBadges.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent mesBadges = new Intent(ProfilUser.this, ListeBadge.class);
				startActivity(mesBadges);
			}
		});
		
		// Ouvrir l'intent mes missions
		bMissions.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent missions = new Intent(ProfilUser.this, ListeMission.class);
				startActivity(missions);
			}
		});
		
		// Ouvrir l'intent mes groupes
		bGroupes.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent groupes = new Intent(ProfilUser.this, ListeGroupe.class);
				startActivity(groupes);
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
		getMenuInflater().inflate(R.menu.profil_user, menu);
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
	public void onClick(View v) {
		
	}

	@Override
	public void processResult(int status, String data) {
		if(status == 200){
			JSONObject json;
			try {
				json = new JSONObject(data);
				nomPrenom.setText(json.getString("firstName")+" "+json.getString("lastName"));
				points.setText("Points: "+json.getString("numberOfPoints"));
			} catch (JSONException ex) {
				ex.printStackTrace();
			}
		}else if(status == 400){
			ToastCours msg = new ToastCours(data, ProfilUser.this);
			ProfilUser.this.finish();
		}
		
	}

}
