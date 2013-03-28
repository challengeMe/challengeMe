package ch.comem.connexion;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

/**
 * class qui permet de faire une nouvelle connexion vers une application REST
 * 
 * @author bastieneichenberger
 * 
 */
public class Connexion extends AsyncTask<JSONObject, Integer, RestResult> {

	public static final String URL_PATH = "http://10.192.80.199:8080/challengeMeApp/webresources/";
	private RestCallback processResult;
	private String path;
	private REQUEST_METHODE request;

	public Connexion(RestCallback call, String ressource,
			REQUEST_METHODE request) {
		if (call == null || ressource == null || request == null) {
			throw new RuntimeException("paramètre manquant");
		}
		processResult = call;
		this.path = URL_PATH + ressource;
		this.request = request;
	}

	@Override
	protected RestResult doInBackground(JSONObject... params) {
		RestResult restResult = new RestResult();
		HttpURLConnection connexion = null;
		int responseCode = -1;
		try {
			URL url = new URL(this.path);
			connexion = (HttpURLConnection) url.openConnection();
			connexion.setRequestMethod(this.request.toString());
			connexion.setRequestProperty("Content-type", "application/json");
			connexion.setRequestProperty("Accept", "application/json");
			// choix du type de méthode
			if (REQUEST_METHODE.GET == this.request) {
				connexion.connect();
			} else if (REQUEST_METHODE.POST == this.request) {
				String message = params[0].toString();
				connexion.setDoOutput(true);
				connexion.setFixedLengthStreamingMode(message.length());
				DataOutputStream stream = new DataOutputStream(
						connexion.getOutputStream());
				stream.writeBytes(message);
				stream.flush();
				stream.close();
			} else if (REQUEST_METHODE.PUT == this.request) {
				connexion.connect();
			} else if (REQUEST_METHODE.DELETE == this.request) {
				throw new RuntimeException(
						"methode delete pas encore implementee");
			}
			try {
				// si la connexion renvoie une erreur, on prend le message pour
				// l'envoyer a l'ihm
				InputStream is = connexion.getInputStream();
				String data = this.streamToString(is);
				responseCode = connexion.getResponseCode();
				restResult.setData(data);
				restResult.setStatus(responseCode);
			} catch (IOException ex) {
				InputStream error_stream = connexion.getErrorStream();
				String error_str = null;
				error_str = this.streamToString(error_stream);
				try {
					responseCode = connexion.getResponseCode();
				} catch (IOException e) {
					responseCode = -1;
				}
				restResult.setData(error_str);
				restResult.setStatus(responseCode);
			}
		} catch (Exception e) {
			throw new RuntimeException(
					"probleme d'execution de la methode doingbackground");
		}

		return restResult;
	}

	public String streamToString(InputStream is) {
		String data_str = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			data_str = sb.toString();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data_str;
	}

	/**
	 * methode appelee apres l'appel REST
	 */
	@Override
	protected void onPostExecute(RestResult result) {
		processResult.processResult(result.getStatus(), result.getData());
	}

	public enum REQUEST_METHODE {
		GET, POST, PUT, DELETE;
		@Override
		public String toString() {
			return this.name();
		}
	}

}
