package ch.comem.vues;

import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import ch.comem.challengeMe.R;
import ch.comem.utilitaire.ToastCours;

public class AddNewMission extends Activity {
	
	private DatePicker dateMissionEnd;
	private Calendar dateMissionStart;
	private int day;
	private int month;
	private int year;
	private String startMission;
	private String timeMission;
	private EditText titleMission;
	private EditText descrMission;
	private EditText pointsMission;
	private Button bAssign;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_mission);
		// Show the Up button in the action bar.
		setupActionBar();
		
		// Identifier les views
		dateMissionEnd = (DatePicker) findViewById(R.id.datePickerMission);
		titleMission = (EditText) findViewById(R.id.titleNewMission);
		descrMission = (EditText) findViewById(R.id.descNewMission);
		pointsMission = (EditText) findViewById(R.id.nbrePoints);
		bAssign = (Button) findViewById(R.id.assignTo);
		
		// Format de la date de début
		dateMissionStart = Calendar.getInstance();
		day = dateMissionStart.get(Calendar.DAY_OF_MONTH);
		month = dateMissionStart.get(Calendar.MONTH);
		year = dateMissionStart.get(Calendar.YEAR);
		startMission = day+"-"+month+"-"+year+" 12:00";
		
		// Format de la date de fin
		day = dateMissionEnd.getDayOfMonth();
		month = dateMissionEnd.getMonth();
		year = dateMissionEnd.getYear();
		timeMission = day+"-"+month+"-"+year+" 12:00";
		
		//Log.i("DATEDEBUT",startMission);
		//Log.i("DATEFIN",timeMission);
		
		bAssign.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(titleMission.getText().toString().isEmpty() || descrMission.getText().toString().isEmpty() || pointsMission.getText().toString().isEmpty()){
					ToastCours toast = new ToastCours("Veuillez remplir tous les champs", AddNewMission.this);
				}else{
					final JSONObject jsonMission = new JSONObject();
					try {
						jsonMission.put("titre",titleMission.getText().toString());
						jsonMission.put("description",descrMission.getText().toString());
						jsonMission.put("dateMission",startMission);
						jsonMission.put("dateFin",timeMission);
						jsonMission.put("nbPoints",pointsMission.getText().toString());
						jsonMission.put("statut","ENCOURS");
						jsonMission.put("categorie","mission de test");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
					Intent assign = new Intent(AddNewMission.this, AssignNewMission.class);
					assign.putExtra("NewMission", jsonMission.toString());
					startActivity(assign);
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
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
