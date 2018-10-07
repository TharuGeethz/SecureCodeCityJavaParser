package com.scc.util;


import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.scc.models.MethodBody;

/**
 * @author Tharushi Geethma Abeysinghe
 */
public class MethodErrorVisitor extends VoidVisitorAdapter<Void> {

    //new constructor  of  MethodErrorVisitor
    int lineNum;
    MethodBody mb;

    public MethodErrorVisitor(int lineNo, MethodBody mbo) {
        this.lineNum = lineNo;
        this.mb = mbo;
    }

    @Override
    public void visit(MethodDeclaration n, Void arg) {
        String[] BeginningLine = n.getBegin().toString().replace("Optional[(", "").replace(")]", "").split(",");
        String[] EndLine = n.getEnd().toString().replace("Optional[(", "").replace(")]", "").split(",");
        String start = BeginningLine[0].split(" ")[1];
        String end = EndLine[0].split(" ")[1];

        if (Integer.parseInt(start) <= lineNum && lineNum <= Integer.parseInt(end)) {
            //System.out.println(n);

            mb.setStart(Integer.parseInt(start));
            mb.setEnd(Integer.parseInt(end));
            mb.setBody(n.toString());
            mb.setErrorLine(lineNum);


        }

        super.visit(n, arg);

    }


}
