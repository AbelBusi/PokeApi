package controlador;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.json.JSONObject;

/**
 *
 * @author cesar
 */
public class cPokeApi {

    public void mostrarPokemon(JTable tablaPok, JTextField txtBuscador, JTextField txtnombre, JTextField txtpeso, JTextField txtAltura, JTextField txtExperiencia, JLabel imagen) {

        DefaultTableModel modelo = new DefaultTableModel();

        String[] nombrecolumnasPok = {"nombre", "peso", "altura"};
        modelo.setColumnIdentifiers(nombrecolumnasPok);

        tablaPok.setModel(modelo);

        try {

            URL url = new URL("https://pokeapi.co/api/v2/pokemon/" + txtBuscador.getText());
            HttpURLConnection com = (HttpURLConnection) url.openConnection();
            com.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(com.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);

                reader.close();

            }
            
            JSONObject jSONObject = new JSONObject(response.toString());
            

        } catch (Exception e) {

        }
    }

}
