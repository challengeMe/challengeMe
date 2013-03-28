    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author bastieneichenberger
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JSONException {
        String membre = "{\"id\": 10, \"nom\": \"eichenberger\", \"prenom\": \"bastien\", \"email\": \"test@gmail.com\", \"playerId\": 18}";
        JSONObject object = new JSONObject(membre);
        Long id = object.getLong("id");
        System.out.println(id);
    }
}
