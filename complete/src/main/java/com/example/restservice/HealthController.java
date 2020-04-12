package com.example.restservice;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.entity.HealthData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Health Monitoring Service", description = "Rest Application used to find the health factor")
@RestController
public class HealthController {

	@Autowired
	HealthService healthService;

	@GetMapping("/question/{code}")
	@ApiOperation(value = "Sending Health Related Questions")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Question Sent Successfully"),
			@ApiResponse(code = 400, message = "Bad Service Request - Invalid input request"),
			@ApiResponse(code = 401, message = "You are not authorized to use the service"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The service you were trying to reach is not found") })
	
	public ResponseEntity<List<String>> getQuestions(@PathVariable String code) {
		List<String> response = new ArrayList<String>();
		response.add(SecurityQuestionConstants.HIGH_FEVER);
		response.add(SecurityQuestionConstants.BREATHING_PROBLEM);
		response.add(SecurityQuestionConstants.COLD);
		response.add(SecurityQuestionConstants.ROUGH_COUGH);
		response.add(SecurityQuestionConstants.TRAVELHISTORY_OUTSIDE_COUNTRY);
		response.add(SecurityQuestionConstants.TRAVELHISTORY_OUTSIDE_STATE);
		response.add(SecurityQuestionConstants.TRAVELHISTORY_OUTSIDE_DISTRICT);

		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@PostMapping("/health/{code}/save")
	@ApiOperation(value = "Save or Update the Health Data of the user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Health Data stored successfully"),
			@ApiResponse(code = 400, message = "Bad Service Request - Invalid input request"),
			@ApiResponse(code = 401, message = "You are not authorized to use the service"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The service you were trying to reach is not found") })
	
	public ResponseEntity<HealthData> createOrUpdateHealthData(@PathVariable String code,
			@RequestBody @Valid HealthData data) throws RecordNotFoundException {
		HealthData updated = healthService.createOrUpdateHealthData(data);
		return new ResponseEntity<HealthData>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/health/{code}/getAll")
	@ApiOperation(value = "Get All the Health Records of the users")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfuly sent the health records of all the users"),
			@ApiResponse(code = 400, message = "Bad Service Request - Invalid input request"),
			@ApiResponse(code = 401, message = "You are not authorized to use the service"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The service you were trying to reach is not found") })
	
	public ResponseEntity<List<HealthData>> getAllHealthData() {
		List<HealthData> list = healthService.getAllHealthData();
		return new ResponseEntity<List<HealthData>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/health/{code}/{id}")
	@ApiOperation(value = "Get All the Health Records of the particular user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfuly sent the health records of the requested users"),
			@ApiResponse(code = 400, message = "Bad Service Request - Invalid input request"),
			@ApiResponse(code = 401, message = "You are not authorized to use the service"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The service you were trying to reach is not found") })
	

	public ResponseEntity<HealthData> getHealthDataById(@PathVariable("id") Long id) throws RecordNotFoundException {
		HealthData entity = healthService.getHealthById(id);
		return new ResponseEntity<HealthData>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("health/{code}/{id}")
	@ApiOperation(value = "Delete Health Records of the particular user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfuly Deleted the health records of the requested users"),
			@ApiResponse(code = 400, message = "Bad Service Request - Invalid input request"),
			@ApiResponse(code = 401, message = "You are not authorized to use the service"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The service you were trying to reach is not found") })
	
	public ResponseEntity<String> deleteHealthDataById(@PathVariable("id") Long id) throws RecordNotFoundException {
		healthService.deleteEmployeeById(id);
		return ResponseEntity.status(HttpStatus.OK).body("User Health Records has been deleted");
	}

	@GetMapping("/healthCalculate/{code}/{id}")
	@ApiOperation(value = "Calculation of the Health Report of the user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Returned the Health Report for the requested user"),
			@ApiResponse(code = 400, message = "Bad Service Request - Invalid input request"),
			@ApiResponse(code = 401, message = "You are not authorized to use the service"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The service you were trying to reach is not found") })
	
	public ResponseEntity<HealthResponse> calculateHealthById(@PathVariable("id") Long id)
			throws RecordNotFoundException {

		HealthResponse healthResponse = new HealthResponse();

		// Getting HealthData for the userId
		HealthData entity = healthService.getHealthById(id);

		int risk = 0;
		String riskFactor = null;
		String response = null;
		if (entity != null) {
			if (entity.getBreathProblem().equalsIgnoreCase("yes")) {
				risk = 20;
			}
			if (entity.getCold().equalsIgnoreCase("yes"))
				risk = risk + 15;

			if (entity.getDryCough().equalsIgnoreCase("yes")) {
				risk = risk + 15;
			}
			if (entity.getHighFever().equalsIgnoreCase("yes")) {
				risk = risk + 15;
			}

			if (entity.getTravelOutsideCountry().equalsIgnoreCase("yes")) {
				risk = risk + 40;
			}

			if (entity.getTravelOutsideDistrict().equalsIgnoreCase("yes")) {
				risk = risk + 15;
			}

			if (entity.getTravelOutsideState().equalsIgnoreCase("yes")) {
				risk = risk + 15;
			}

			if (risk == 0) {
				riskFactor = "No Risk";
				response = "Stay at Home and Be Safe";

			} else if (risk <= 25) {
				riskFactor = "Low Risk";
				response = "Take care of your health and check the health status tommorow";
			}

			else if (risk > 25 && risk <= 50) {
				riskFactor = "Moderate Risk";
				response = "Take care of your health, Please self quarantine yourself and check the health status tommorow";
			}

			else if (risk > 50 && risk <= 70) {
				riskFactor = "High Risk";
				response = "Please self quarantine yourself and take a medical test";
			}

			else if (risk > 70) {
				riskFactor = "Very High Risk";
				response = "Call the government helpline number and please get admitted to the hospital";
			}
		}
		else {
			healthResponse.setMessage("User Record has been not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(healthResponse);
		}

		healthResponse.setMessage(response);
		healthResponse.setRiskFactor(riskFactor);

		return ResponseEntity.status(HttpStatus.OK).body(healthResponse);

	}

}
