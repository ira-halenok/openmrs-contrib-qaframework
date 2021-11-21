/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * 
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.assertTrue;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.FindPatientPage;
import org.openmrs.contrib.qaframework.page.PatientCaptureVitalsPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class VitalsAndTriagingSteps extends Steps {

	private PatientCaptureVitalsPage patientCaptureVitalsPage;
	private TestData.PatientInfo testPatient;

	@Before(RunTest.HOOK.SELENIUM_VITALS)
	public void visitPatientDashboard() {
		testPatient = createTestPatient();
		initiateWithLogin();
		findPatientPage = (FindPatientPage) homePage.goToFindPatientRecord().waitForPage();
		findPatientPage.enterPatient(testPatient.identifier);
		findPatientPage.waitForPageToLoad();
		dashboardPage = findPatientPage.clickOnFirstPatient();
		dashboardPage.startVisit().waitForPage();
	}

	@After(RunTest.HOOK.SELENIUM_VITALS)
	public void destroy() {
		deletePatient(testPatient);
		quit();
	}

	@Given("a user clicks on Capture Vitals link from Patient dashboard")
	public void loadVitalsPage() {
		patientCaptureVitalsPage = (PatientCaptureVitalsPage) dashboardPage.goToPatientCaptureVitalsPage().waitForPage();
	}

	@Then("the system loads Vitals page")
	public void systemloadsVitalsPage() {
		assertTrue(textExists("Vitals"));
	}

	@When("a user enters normal patient vitals")
	public void enterNormalPatientVitals() {
		patientCaptureVitalsPage.setHeightField("164");
		patientCaptureVitalsPage.setWeightField("54");
		patientCaptureVitalsPage.setTemperatureField("37");
		patientCaptureVitalsPage.setPulseField("60");
		patientCaptureVitalsPage.setRespiratoryField("16");
		patientCaptureVitalsPage.setBloodPressureFields("60", "90");
		patientCaptureVitalsPage.setBloodOxygenSaturationField("98");
	}

	@When("a user enters a height \"([^\"]*)\"$")
	public void crossCheckMinimumHeightValidity(String height) {
		patientCaptureVitalsPage.setHeightField(height);
	}

	@When("a user enters a \"([^\"]*)\" \"([^\"]*)\"$")
	public void crossCheckMinimumHeightValidity(String attrName, String value) {
		if (attrName.equals("height")) {
			patientCaptureVitalsPage.setHeightField(value);
		} else if (attrName.equals("weight")) {
			patientCaptureVitalsPage.clickNext(1);
			patientCaptureVitalsPage.setWeightField(value);
		} else if (attrName.equals("temperature")) {
			patientCaptureVitalsPage.clickNext(3);
			patientCaptureVitalsPage.setTemperatureField(value);
		} else if (attrName.equals("pulse")) {
			patientCaptureVitalsPage.clickNext(4);
			patientCaptureVitalsPage.setPulseField(value);
		} else if (attrName.equals("respiratory rate")) {
			patientCaptureVitalsPage.clickNext(5);
			patientCaptureVitalsPage.setRespiratoryField(value);
		} else if (attrName.equals("systolic blood pressure")) {
			patientCaptureVitalsPage.clickNext(6);
			patientCaptureVitalsPage.setBloodPressureFields(value, "90");
		} else if (attrName.equals("diastolic blood pressure")) {
			patientCaptureVitalsPage.clickNext(6);
			patientCaptureVitalsPage.setBloodPressureFields("90", value);
		} else if (attrName.equals("saturation")) {
			patientCaptureVitalsPage.clickNext(7);
			patientCaptureVitalsPage.setBloodOxygenSaturationField(value);
		}
	}

	@Then("^the system shows warning message \"([^\"]*)\"$")
	public void systemShowsWarningMessage(String message) {
		assertTrue(patientCaptureVitalsPage.containsText(message));
	}


	@When("a user enters normal patient vitals \\(low value boundary)")
	public void enterNormalPatientVitalsLowerBoundaryValue() {
		patientCaptureVitalsPage.setHeightField("10");
		patientCaptureVitalsPage.setWeightField("0");
		patientCaptureVitalsPage.setTemperatureField("25");
		patientCaptureVitalsPage.setPulseField("0");
		patientCaptureVitalsPage.setRespiratoryField("0");
		patientCaptureVitalsPage.setBloodPressureFields("50", "30");
		patientCaptureVitalsPage.setBloodOxygenSaturationField("0");
	}

	@When("a user enters normal patient vitals \\(high value boundary)")
	public void enterNormalPatientVitalsHighBoundaryValue() {
		patientCaptureVitalsPage.setHeightField("272");
		patientCaptureVitalsPage.setWeightField("250");
		patientCaptureVitalsPage.setTemperatureField("43");
		patientCaptureVitalsPage.setPulseField("230");
		patientCaptureVitalsPage.setRespiratoryField("99");
		patientCaptureVitalsPage.setBloodPressureFields("250", "150");
		patientCaptureVitalsPage.setBloodOxygenSaturationField("100");
	}

	@And("a user clicks on save button")
	public void savePatientVitals() {
		patientCaptureVitalsPage.confirm();
		patientCaptureVitalsPage.save();
		patientCaptureVitalsPage.waitForPage();
	}

	@Then("the system adds patient vitals into the vitals table")
	public void systemAddsPatientVitals() {
		assertTrue(patientCaptureVitalsPage.containsText("Vitals"));
	}
}
