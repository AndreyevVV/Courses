package com.rpam.rd.autotasks;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

public class CatchEmAll {

    //You may set another exception in this field;
    static Exception exception = new FileNotFoundException();

    public static void riskyMethod() throws Exception {
        throw exception;
    }

    public static void main(String[] args) throws Exception {
        try {
            CatchEmAll.riskyMethod();
        } catch (ArithmeticException | NumberFormatException e){
            System.err.println ((e instanceof ArithmeticException) ? e.getMessage():e.getMessage());
        }
        catch (Exception e) {
            throw (e instanceof FileNotFoundException) ? new IllegalArgumentException("Resource is missing", e):
                  (e instanceof IOException) ? new IllegalArgumentException("Resource error", e): e;
        }
    }
}



/*
catch (Somexception1 | Someexeption2 | SomeOtherException ex) {
        throw (e instanceof Somexception1) ? (Somexception1) e : new Somexception1(e);
        }*/
/*
        return leftMostPoint = leftMostPoint.getX() < b.getX()  &&
                                leftMostPoint.getX() < c.getX() ? leftMostPoint :
                                b.getX() < c.getX() ? b : c;*/
