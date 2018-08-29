package com.scc.controllers;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.scc.common.ErrorResponse;
import com.scc.models.ClassError;
import com.scc.models.ClassPath;
import com.scc.models.MethodBody;
import com.scc.models.MethodParams;
import com.scc.util.MethodDetailsVisitor;
import com.scc.util.MethodErrorVisitor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;

@RestController
public class MethodController {

    private static String output(InputStream inputStream) throws IOException {

        StringBuilder sb = new StringBuilder();

        BufferedReader br = null;

        try {

            br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + System.getProperty("line.separator"));
            }
        } finally {
            br.close();
        }
        return sb.toString();
    }

    @RequestMapping(value = "/method/details", method = RequestMethod.POST)
    public ResponseEntity<?> sendMethodParas(@RequestBody ClassPath currentClassPath) {

        ArrayList<MethodParams> methodParamsList = new ArrayList<>();

        try {
            //        File file = new File(currentClassPath.getPath());
            // creates an input stream for the file to be parsed
            FileInputStream in = new FileInputStream(currentClassPath.getPath());
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

    @RequestMapping(value = "/method/errodetails", method = RequestMethod.POST)
    public ResponseEntity<?> getMethodDetails(@RequestBody ClassError classError) {

        try {
            int lineNo = classError.getLineNum();
            MethodBody mb = new MethodBody();
            File file = new File(classError.getPath());

            // creates an input stream for the file to be parsed
            FileInputStream in = new FileInputStream(file);
            // parse it

            CompilationUnit cu = JavaParser.parse(in);

            // visit and print the methods names
            cu.accept(new MethodErrorVisitor(lineNo, mb), null);


            return new ResponseEntity<>(mb, HttpStatus.OK);

        } catch (FileNotFoundException e) {

            return new ResponseEntity<>(
                    new ErrorResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), e.getMessage()),
                    HttpStatus.NOT_FOUND
            );
        }

    }

    @GetMapping("/start")
    public void startIntellij() throws Exception {
//        String arguement = " --line 6 ~/home/ghostman/test/StreamTest/src/com/test/StreamTest.java";
//        Process procBuildScript = new ProcessBuilder("path/to/myScript.sh", "myArg1 myArg2").start();
//        ProcessBuilder processBuilder = new ProcessBuilder("~/home/ghostman/software/idea-IU-181.5087.20/bin/idea.sh", arguement);
        try {
//            processBuilder.start();
            ProcessBuilder pb = new ProcessBuilder("cd & pwd & cd software/idea-IU-181.5087.20/bin & ./idea.sh --line 6 /home/ghostman/test/StreamTest/src/com/test/StreamTest.java");
            Process process = pb.start();
//            pb.command("cd & pwd & cd software/idea-IU-181.5087.20/bin & ./idea.sh --line 6 /home/ghostman/test/StreamTest/src/com/test/StreamTest.java");
            int errCode = process.waitFor();

            System.out.println("Echo Output:\n" + output(process.getInputStream()));

        } catch (IOException e) {
            throw new IOException();
        } catch (InterruptedException e) {
            throw new InterruptedException();
        }
    }


}
