/**
 *
 */
package com.mystore.base;
import org.openqa.selenium.WebDriver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

//import com.tns.automation.cucumber.core.SoftAssertions;

import io.cucumber.java.Scenario;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Set;


/**
 *
 *
 */
public class WebScenarioContext {

	private Scenario scenario;

	private HashMap<String, Object> contextData;

//	private SoftAssertions softAssertions;

	/**
	 * Gets scenario.
	 *
	 * @return the scenario
	 */
	public Scenario getScenario() {
		return scenario;
	}

	/**
	 * Sets scenario.
	 *
	 * @param scenario the scenario
	 */
	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

	/**
	 * Gets softAssertions.
	 *
	 * @return the softAssertions
	 */
//	public SoftAssertions getSoftAssertions() {
//		if (softAssertions == null) {
//			softAssertions = new SoftAssertions();
//		}
//		return softAssertions;
//	}

	/**
	 * Sets softAssertions.
	 *
	 * @param softAssertions the scenario
	 */
//	public void setSoftAssertions(SoftAssertions softAssertions) {
//		this.softAssertions = softAssertions;
//	}

	/**
	 * Is in context boolean.
	 *
	 * @param item the item
	 * @return the boolean
	 */
	public boolean isInContext(String item) {
		if (contextData == null) {
			contextData = new HashMap<String, Object>();
		}
		return contextData.containsKey(item);
	}

	/**
	 * Gets context data.
	 *
	 * @param item the item
	 * @return the context data
	 */
	public Object getContextData(String item) {
		if (contextData == null) {
			contextData = new HashMap<String, Object>();
		}
		return contextData.get(item);
	}

	/**
	 * Sets context data.
	 *
	 * @param item  the item
	 * @param value the value
	 */
	public void setContextData(String item, Object value) {
		if (contextData == null) {
			contextData = new HashMap<String, Object>();
		}

		if (contextData.containsKey(item)) {
			logKey(item);
		}
		contextData.put(item, value);
	}

	/**
	 * Clear context data.
	 */
	public void clearContextData() {
		logContextData();
		contextData = null;

	}

	private void logContextData() {
		if (contextData != null) {
			Set<String> keys = contextData.keySet();
			for (String key : keys) {
				logKey(key);
			}
		}
	}

	private void logKey(String item) {
		if (contextData != null) {
			Object obj = contextData.get(item);
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = "";
			try {
				if (obj != null)
					json = ow.writeValueAsString(obj);
				else
					json = "null";
			} catch (JsonProcessingException e) {
				json = obj.toString();
			}
			 if (obj != null) {
			 scenario.log("Context Data " + item + " of type " + obj.getClass() + ": " +
			 json);
			 } else {
			 scenario.log("Scenario Data is null");
			 }
		}
	}


}
