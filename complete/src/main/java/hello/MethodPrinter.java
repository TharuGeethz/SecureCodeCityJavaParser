/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;
import java.io.*;

import com.github.javaparser.*;
import com.github.javaparser.ast.CompilationUnit;

import com.github.javaparser.ast.body.MethodDeclaration;

import com.github.javaparser.ast.visitor.*;


/**
 *
 * @author tharu
 */

    public class MethodPrinter {

    public static void main(String[] args) throws Exception {
        File file = new File("/home/tharu/gs-messaging-jms/complete/src/main/java/hello/Tharu.java");
        // creates an input stream for the file to be parsed
        FileInputStream in = new FileInputStream(file);

        // parse it
        CompilationUnit cu = JavaParser.parse(in);

        // visit and print the methods names
        cu.accept(new MethodVisitor(), null);
    }

    /**
     * Simple visitor implementation for visiting MethodDeclaration nodes.
     */
    private static class MethodVisitor extends VoidVisitorAdapter<Void> {
       @Override
        public void visit(MethodDeclaration n, Void arg) {
            /* here you can access the attributes of the method.
             this method will be called for all methods in this 
             CompilationUnit, including inner class methods */
            System.out.println(n.getName());
            System.out.println("The begining : ");
            String[] BeginningLine =n.getBegin().toString().replace("Optional[(", "").replace(")]", "").split(",");
            System.out.println(BeginningLine[0]);
            
            //System.out.println(n.getBegin().toString().replace("Optional[(", "").replace(")]", "").split(","));
            
            System.out.println("The end : ");
            String[] EndLine =n.getEnd().toString().replace("Optional[(", "").replace(")]", "").split(",");
            System.out.println(EndLine[0]);
            //System.out.println(n.getEnd().toString().replace("Optional[(", "").replace(")]", ""));
            System.out.println("\n");
            super.visit(n, arg);
        }
    }
}
    
    
    
    

