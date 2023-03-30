package by.teachmeskills.petstore;

import by.teachmeskills.api.client.PetApiClient;
import by.teachmeskills.api.dto.ApiResponse;
import by.teachmeskills.api.dto.pet.Category;
import by.teachmeskills.api.dto.pet.Pet;
import by.teachmeskills.api.dto.pet.Status;
import by.teachmeskills.api.dto.pet.Tag;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;

public class PetCrudTest {
    private Faker faker = new Faker();
    private Pet createdPet;
    private Pet expectedPet;
    private Pet actualPet;

    @Test
    public void createPet() {
        createdPet = createPetExample();
        expectedPet = new PetApiClient().postAddPet(createdPet);
        actualPet = new PetApiClient().getPet(expectedPet.getId());
        Assertions.assertThat(actualPet)
                .as("Actual and expected results should be equal")
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedPet);
    }

    @Test
    public void updatePet() {
        createdPet = createPetExample();
        createdPet.setStatus(Status.SOLD);
        expectedPet = new PetApiClient().putUpdatePet(createdPet);
        actualPet = new PetApiClient().getPet(expectedPet.getId());
        Assertions.assertThat(actualPet)
                .as("Actual and expected results should be equal")
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedPet);

        /*createdPet.setId(0014);
        expectedPet = given().
                contentType(ContentType.JSON).
                body(createdPet).
                when().
                log().ifValidationFails(LogDetail.ALL, true).
                put("https://petstore.swagger.io/v2/pet").
                then().
                log().ifValidationFails(LogDetail.ALL, true).
                statusCode(200)
                .body("id", not(empty()))
                .extract().body().as(Pet.class);

        actualPet = given().contentType(ContentType.JSON)
                .pathParam("id", expectedPet.getId())
                .when()
                .get("https://petstore.swagger.io/v2/pet/{id}")
                .then()
                .statusCode(200)
                .extract().body().as(Pet.class);

        Assertions.assertThat(actualPet)
                .as("Actual and expected results should be equal")
                .isEqualTo(expectedPet);*/
    }

    @Test
    public void deletePet() {
        createdPet = createPetExample();
        expectedPet = new PetApiClient().postAddPet(createdPet);
        ApiResponse response = new PetApiClient().deletePet(expectedPet.getId());

        Assertions.assertThat(response.getMessage())
                .as("DELETE /pet/{id} response contains incorrect message (doesn't equal to id)")
                .startsWith(String.valueOf(expectedPet.getId()));

        Response actPet = new PetApiClient().getPetResponse(expectedPet.getId());
        Assertions.assertThat(actPet.getStatusCode())
                .as("Status code should be 404 for deleted element")
                .isEqualTo(HttpStatus.SC_NOT_FOUND);
    }

    private Pet createPetExample() {
        return createdPet = Pet.builder()
                .id(0)
                .name(faker.animal().name())
                .status(Status.AVAILABLE)
                .photoUrls(List.of(faker.internet().image()))
                .category(new Category(faker.number().randomDigit(), faker.funnyName().name()))
                .tags(List.of(new Tag(faker.number().randomDigit(), "dog")))
                .build();
    }
}
