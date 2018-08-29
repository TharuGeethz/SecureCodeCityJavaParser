package com.scc.util;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.scc.models.MethodParams;

import java.util.ArrayList;

public class MethodDetailsVisitor extends VoidVisitorAdapter<Void> {

    ArrayList<MethodParams> lst;

    public MethodDetailsVisitor(ArrayList<MethodParams> lst) {
        this.lst = lst;
    }

    @Override
    public void visit(MethodDeclaration n, Void arg) {

        String[] BeginningLine = n.getBegin().toString().replace("Optional[(", "").replace(")]", "").split(",");
        String[] EndLine = n.getEnd().toString().replace("Optional[(", "").replace(")]", "").split(",");

        String methodName = n.getNameAsString();
        String start = BeginningLine[0].split(" ")[1];
        String end = EndLine[0].split(" ")[1];

        MethodParams parameter = new MethodParams(methodName, start, end);

        lst.add(parameter);
        super.visit(n, arg);
    }
}