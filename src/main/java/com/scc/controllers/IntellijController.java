package com.scc.controllers;

import com.scc.models.IntelliJData;
import com.scc.models.api.IntellijStartStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author Tharushi Geethma Abeysinghe
 */
@RestController
public class IntellijController {

    @PostMapping("/startIntellij")
    public ResponseEntity<?> startIntellij(@RequestBody IntelliJData intelliJData) {

        try {

            String lineNumber = intelliJData.getLineNumber();
            String pathOfFileToOpen = intelliJData.getFilePathToOpen();

            ProcessBuilder processBuilder = new ProcessBuilder("./intellijrunner.sh", lineNumber, pathOfFileToOpen);
            Process process = processBuilder.start();

            IntellijStartStatus startStatus = new IntellijStartStatus();

            if (process.getOutputStream() != null) {

                startStatus.setStatus("success");
                startStatus.setMessage("Intellij IDEA started");
                return new ResponseEntity<>(
                        startStatus, HttpStatus.OK
                );

            } else {

                startStatus.setStatus("failed");
                startStatus.setMessage("Intellij IDEA starting failed");
                return new ResponseEntity<>(
                        startStatus, HttpStatus.NOT_FOUND
                );
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());

            IntellijStartStatus startStatus = new IntellijStartStatus();
            startStatus.setStatus("failed");
            startStatus.setMessage("Intellij IDEA starting failed");

            return new ResponseEntity<>(
                    startStatus, HttpStatus.NOT_FOUND
            );
        }
    }

}
