package ch.comem.vues;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ch.comem.challengeMe.R;
import ch.comem.connexion.AppPrefs;

public class ChallengeMeMain extends Activity {
	
	private Button bLogin;
	private Button bRegister;
	private EditText tidPlayer;
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_challenge_me);
		
		// Identifier les views
		bLogin = (Button) findViewById(R.id.login);
		bRegister = (Button) findViewById(R.id.register);
		tidPlayer = (EditText) findViewById(R.id.idPlayer);


		// Ouvrir l'intent du bouton Login
		bLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String test = tidPlayer.getText().toString();
				
				Context context = getApplicationContext();
				AppPrefs appPrefs = new AppPrefs(context);
				appPrefs.setUser_id(Integer.parseInt(test));
								
				Intent connexion = new Intent(ChallengeMeMain.this, ProfilUser.class);
				startActivity(connexion);
			}
		});
		
		// Ouvrir l'intent du bouton Inscription
		bRegister.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent enregistrer = new Intent(ChallengeMeMain.this, AddNewUser.class);
				startActivity(enregistrer);
			}
		});
	}
}
