package by.teachmeskills.api.client;

import by.teachmeskills.api.dto.ApiResponse;
import by.teachmeskills.api.dto.pet.Pet;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.Map;

public class PetApiClient extends BaseApiClient {

    public static final String PET_PATH = "/v2/pet";
    public static final String PET_ID_PATH = "/v2/pet/{id}";

    public Pet postAddPet(Pet body) {
        return post(PET_PATH, body, Pet.class);
    }

    public Pet getPet(long id) {
        return get(PET_ID_PATH, Map.of("id", id))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(Pet.class);
    }

    public Response getPetResponse(long id) {
        return get(PET_ID_PATH, Map.of("id", id));
    }

    public ApiResponse getApiResponse(long id, int statusCode) {
        return get(PET_ID_PATH, Map.of("id", id))
                .then()
                .statusCode(statusCode)
                .extract()
                .body()
                .as(ApiResponse.class);
    }

    public Pet putUpdatePet(Pet body) {
        return put(PET_PATH, body)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(Pet.class);
    }

    public ApiResponse deletePet(long id) {
        return delete(PET_ID_PATH, Map.of("id", id))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(ApiResponse.class);
    }
}
