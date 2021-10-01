package com.identity.steps;

import com.identity.helper.CsvUtils;
import com.identity.model.VehicleDetails;
import com.identity.model.World;
import com.identity.pageObjects.HomePage;
import com.identity.pageObjects.ResultsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class StepDefs {

    private HomePage homePage;
    private ResultsPage resultsPage;

    public StepDefs(HomePage homePage, ResultsPage resultsPage) {
        this.homePage = homePage;
        this.resultsPage = resultsPage;
    }


    @Given("the car reg details are extracted from files in {string} directory")
    public void theCarRegDetailsAreExtractedFromFilesInDirectory(String dirName) {
        World.vehicleRegsList = CsvUtils.getVehicleRegs(dirName);
    }


    @And("the expected car details are extracted from files in {string} directory")
    public void theExpectedCarDetailsAreExtractedFromFilesInDirectory(String dirName) {
        World.vehicleDetailsList = CsvUtils.getVehicleDetails(dirName);
    }

    @When("the user is on Car Check Home page")
    public void theUserIsOnCarCheckHomePage() throws InterruptedException {
        homePage.startPage();
        Assert.assertEquals("Car Tax Check | Free Car Check", homePage.getPageTitle());
    }


    @Then("compare vehicle reg search results with expected output data")
    public void comparedVehicleRegSearchDetailsWithExtractedOutputDataFor() {


        for (String vehicleReg : World.vehicleRegsList) {
            // BW57 BOW vehicle details are not found
            boolean exists = World.vehicleDetailsList.stream().anyMatch(s -> s.getRegistration().contains(vehicleReg.replaceAll("\\s+","")));
            if (!exists)
                continue;
            homePage.enterCarReg(vehicleReg);

            homePage.clickFullCheck();
            homePage.clickCheckVehicle();
            assertThat(resultsPage.getPageTitle(), containsString("Car Tax Check | Car History Check |"));

            for (VehicleDetails vehicleDetails : World.vehicleDetailsList) {
                if (vehicleDetails.getRegistration().contains(vehicleReg)) {
                    Assert.assertEquals(vehicleDetails.getRegistration(), resultsPage.getRegistration());
                    assertThat(resultsPage.getModel(), containsString(vehicleDetails.getMake()));
                    Assert.assertEquals(vehicleDetails.getColor(), resultsPage.getColour());
                    Assert.assertEquals(vehicleDetails.getYear(), resultsPage.getYear());
                    resultsPage.clickCarTaxCheckIcon();
                    break;
                }

            }


        }

    }


}
