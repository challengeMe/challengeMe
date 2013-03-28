package ch.comem.connexion;

/**
 * class qui permet de crer un resultat REST
 * int, le statut 200 OK ou 404
 * data, une String json
 * @author bastieneichenberger
 *
 */
public class RestResult {
	
	private int status;
	private String data;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
	
}