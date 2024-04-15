package controlador;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

            }
            reader.close();

            JSONObject jSONObject = new JSONObject(response.toString());

            String name = jSONObject.getString("name");
            int weight = jSONObject.getInt("weight");
            int height = jSONObject.getInt("height");
            int experience = jSONObject.getInt("base_experience");
            String imageUrl = jSONObject.getJSONObject("sprites").getString("front_default");

            
            modelo.addRow(new Object[]{name, weight, height});
            txtnombre.setText(name);
            txtpeso.setText(String.valueOf(weight));
            txtAltura.setText(String.valueOf(height));
            txtExperiencia.setText(String.valueOf(experience));
            ImageIcon icon = new ImageIcon(new URL(imageUrl));
            imagen.setIcon(icon);

        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error de conexion a la api");

        }
    }

}
