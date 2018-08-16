/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;
import java.io.*;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.*;
import java.io.File;



/**
 *
 * @author tharu
 */

    
 public class CuPrinter {

    public static void main(String[] args) throws Exception {
        File file = new File("/home/tharu/gs-messaging-jms/complete/src/main/java/hello/Email.java");
        
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBEFOREEEEEEEEEEE");
        // creates an input stream for the file to be parsed
        FileInputStream in = new FileInputStream(file);
System.out.println("First jumpppppppppp");
        // parse the file
        CompilationUnit cu = JavaParser.parse(in);

        // prints the resulting compilation unit to default system output
        System.out.println(cu.toString());
    }
}
    
    
    
    

