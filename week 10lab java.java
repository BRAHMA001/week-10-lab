import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public class Main {
    public static class APIUtility {
        public static String callAPI(String apiUrl) throws IOException {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            bufferedReader.close();

            return response.toString();
        }
    }

    public static class Model {
        private String baseCode;
        private String conversionRates;
        private String result;
        // Add other properties based on the JSON structure

        // Constructors, getters, and setters

        @Override
        public String toString() {
            return "Model{" +
                    "baseCode='" + baseCode + '\'' +
                    ", conversionRates='" + conversionRates + '\'' +
                    ", result='" + result + '\'' +
                    // Add other properties to the output
                    '}';
        }
    }

    public static void main(String[] args) {
        try {
            String apiKey = "a8bba08af1f10b38714233ef";
            String apiUrl = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";
            String jsonResponse = APIUtility.callAPI(apiUrl);

            // Using Gson library to convert JSON into Java objects
            Gson gson = new Gson();
            Model model = gson.fromJson(jsonResponse, Model.class);

            // Print the converted Java object
            System.out.println(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
