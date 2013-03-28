package ch.comem.vues;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ch.comem.challengeMe.R;
import ch.comem.connexion.AppPrefs;
import ch.comem.connexion.Connexion;
import ch.comem.connexion.Connexion.REQUEST_METHODE;
import ch.comem.connexion.RestCallback;
import ch.comem.utilitaire.ToastCours;

public class AddNewUser extends Activity implements RestCallback {
	
	private Button envoyer;
	private EditText nom;
	private EditText prenom;
	private EditText email;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_user);
		// Show the Up button in the action bar.
		setupActionBar();
		
		// Identifier les views
		envoyer = (Button) findViewById(R.id.addUser);
		nom = (EditText) findViewById(R.id.lastName);
		prenom = (EditText) findViewById(R.id.firstName);
		email = (EditText) findViewById(R.id.mail);
		
		envoyer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Recuperer les infos
				String nomUser = nom.getText().toString();
				String prenomUser = prenom.getText().toString();
				String emailUser = email.getText().toString();
				// Tester les champs
				if(nomUser.isEmpty() || prenomUser.isEmpty() || emailUser.isEmpty()){
					ToastCours toast = new ToastCours("Veuillez remplir tous les champs", AddNewUser.this);
				}
				else{
					// Transmettre en JSON
					JSONObject json = new JSONObject();
		            try {
						json.put("nom",nomUser);
			            json.put("prenom",prenomUser);
			            json.put("email",emailUser);
					} catch (JSONException e) {
						e.printStackTrace();
					}
		            // POST le membre
		            Connexion connexion = new Connexion(AddNewUser.this, "membres", REQUEST_METHODE.POST);
		 	       	connexion.execute(json);
				}
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
		getMenuInflater().inflate(R.menu.add_new_user, menu);
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
		if(status == 200){
			int idUser = 0;
			String nameUser = null;
			JSONObject joueur;
			try {
				joueur = new JSONObject(data);
				idUser = joueur.getInt("id");
				nameUser = joueur.getString("nom");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			ToastCours toast = new ToastCours("bienvenue "+nameUser, AddNewUser.this);
			// mettre id en cache
			Context context = getApplicationContext();
			AppPrefs appPrefs = new AppPrefs(context);
			appPrefs.setUser_name(nameUser);
			appPrefs.setUser_id(idUser);
					    
		    Intent connexion = new Intent(AddNewUser.this, ProfilUser.class);
			startActivity(connexion);
		}
	}
	
}
