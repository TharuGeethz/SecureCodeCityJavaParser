package com.scc.controllers;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.scc.models.ClassPath;
import com.scc.models.MethodParameter;
import com.scc.util.MethodVisitor;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

    @RequestMapping(value = "/abc", method = RequestMethod.POST)
    public List<MethodParameter> sendMethodParas(@RequestBody ClassPath currentClassPath) throws FileNotFoundException {
        ArrayList<MethodParameter> lst = new ArrayList<>();

        File file = new File(currentClassPath.getPath());
        // creates an input stream for the file to be parsed
        FileInputStream in = new FileInputStream(currentClassPath.getPath());

        // parse it
        CompilationUnit cu = JavaParser.parse(in);

        // visit and print the methods names
        cu.accept(new MethodVisitor(lst), null);


        return lst;
    }




}
