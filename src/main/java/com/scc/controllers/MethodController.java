package com.scc.controllers;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.scc.common.ErrorResponse;
import com.scc.models.*;
import com.scc.util.MethodDetailsVisitor;
import com.scc.util.MethodErrorVisitor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;

// TODO Add Readme.md file

/**
 * @author Tharushi Geethma Abeysinghe
 */
@RestController
public class MethodController {

    @GetMapping("/method/details")
    public ResponseEntity<?> sendMethodParas(@RequestParam("classPath") String currentClassPath) {

        ArrayList<MethodParams> methodParamsList = new ArrayList<>();

        try {
            // creates an input stream for the file to be parsed
            FileInputStream in = new FileInputStream(currentClassPath);

            // parse it
            CompilationUnit cu = JavaParser.parse(in);

            // visit and print the methods names
            cu.accept(new MethodDetailsVisitor(methodParamsList), null);

            return new ResponseEntity<>(methodParamsList, HttpStatus.OK);

        } catch (FileNotFoundException e) {

            return new ResponseEntity<>(
                    new ErrorResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), e.getMessage()),
                    HttpStatus.NOT_FOUND);
        }


    }

    @RequestMapping(value = "/method/errodetails", method = RequestMethod.GET)
    public ResponseEntity<?> getMethodDetails(@RequestParam("lineNum") String lineNum, @RequestParam("path") String path) {

        try {
            MethodBody mb = new MethodBody();
            File file = new File(path);

            // creates an input stream for the file to be parsed
            FileInputStream in = new FileInputStream(file);

            // parse it
            CompilationUnit cu = JavaParser.parse(in);

            // visit and print the methods names
            cu.accept(new MethodErrorVisitor(Integer.parseInt(lineNum), mb), null);

            return new ResponseEntity<>(mb, HttpStatus.OK);

        } catch (FileNotFoundException e) {

            return new ResponseEntity<>(
                    new ErrorResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), e.getMessage()),
                    HttpStatus.NOT_FOUND
            );
        }

    }

}
