package com.scc.util;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.scc.models.MethodParameter;

import java.util.ArrayList;

public class MethodVisitor extends VoidVisitorAdapter<Void> {

    ArrayList<MethodParameter> lst;

    public MethodVisitor(ArrayList<MethodParameter> lst) {
        this.lst = lst;
    }

    @Override
    public void visit(MethodDeclaration n, Void arg) {
            /* here you can access the attributes of the method.
             this method will be called for all methods in this
             CompilationUnit, including inner class methods */
//        System.out.println(n.getName());
//        System.out.println("The begining : ");
        String[] BeginningLine =n.getBegin().toString().replace("Optional[(", "").replace(")]", "").split(",");

        String[] EndLine =n.getEnd().toString().replace("Optional[(", "").replace(")]", "").split(",");
//        System.out.println(EndLine[0]);
        //System.out.println(n.getEnd().toString().replace("Optional[(", "").replace(")]", ""));
//        System.out.println("\n");
        String methodName = n.getNameAsString();
        String start = BeginningLine[0].split(" ")[1];
        String end = EndLine[0].split(" ")[1];

        MethodParameter parameter = new MethodParameter(methodName, start, end );

        lst.add(parameter);
        super.visit(n, arg);
    }
}