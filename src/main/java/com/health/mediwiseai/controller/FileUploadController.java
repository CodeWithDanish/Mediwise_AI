package com.health.mediwiseai.controller;

import java.security.Principal;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.health.mediwiseai.model.HealthReport;
import com.health.mediwiseai.model.User;
import com.health.mediwiseai.repository.HealthReportRepository;
import com.health.mediwiseai.repository.UserRepository;

import jakarta.servlet.http.HttpSession;


@RestController
public class FileUploadController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private HealthReportRepository healthRepository;
    // Endpoint to handle file upload
    // http://localhost:8080/api/files/upload
	@PostMapping("/upload")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file, HttpSession session) {
	    User user = (User)session.getAttribute("loggedInUser");
	    
	    if (user == null) {
            return ResponseEntity.status(401).body("User not logged in.");
        }
	    
	    // Send file to Python Flask AI server for analysis
	    try {
	        String analysisJson = sendFileToAI(file);
	        JSONObject obj = new JSONObject(analysisJson);

	        HealthReport report = new HealthReport();
	        report.setFileName(file.getOriginalFilename());
	        report.setFileData(file.getBytes());
	        report.setUser(user);
	        report.setDiagnosis(obj.getString("diagnosis"));
	        report.setMedicine(obj.getString("medicine"));
	        report.setCure(obj.getString("cure"));
	        report.setPrecautions(obj.getString("precautions"));

	        healthRepository.save(report);
	        return ResponseEntity.ok("Report uploaded and analyzed successfully!");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(500).body("Something went wrong.");
	    }

	}
	
	

}

