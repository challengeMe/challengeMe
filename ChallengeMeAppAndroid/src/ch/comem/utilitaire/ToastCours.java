package ch.comem.utilitaire;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * class qui permet de faire un Toast court
 * @author eichenberger bastien
 *
 */
public class ToastCours {
	
	private Context contexte;
	private String text;
	int duration;
	
	public ToastCours(String msg, Context context){
		this.contexte = context;
		this.text = msg;
		this.duration = Toast.LENGTH_SHORT;
		
		Toast toast = Toast.makeText(this.contexte, this.text, this.duration);
		toast.show();
	}
	

}
