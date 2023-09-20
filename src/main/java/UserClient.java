import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.List;


public class UserClient {

    private static final String API_BASE_URL = "https://reqres.in/api/users";

    public static List<User> getUsersFromPage(int page) throws IOException {

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(API_BASE_URL + "?page=" + page);

            // get response
            HttpResponse response = httpClient.execute(httpGet);

            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode == 200 ) {
                // retrieve user list
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonResponse = objectMapper.readTree(response.getEntity().getContent());
                JsonNode usersNode = jsonResponse.get("data");
                List<User> userList = objectMapper.convertValue(usersNode, new TypeReference<List<User>>() {});
                return userList;
            } else {
                throw new HttpResponseException(statusCode, response.getStatusLine().getReasonPhrase());
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            int page = 1; // You can change the page number here
            List<User> users = getUsersFromPage(page);
            if (users != null) {
                for (User user : users) {
                    System.out.println("First Name: " + user.getFirstName());

                    System.out.println("Last Name: " + user.getLastName());
                    System.out.println();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

