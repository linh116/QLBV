package com.qlbv.common;

public class Logger {

    public void info(Object message) {
        printMsg("INFO", message);
    }

    public void warning(String message) {
        printMsg("WARNING", message);
    }

    public void error(String message) {
        printMsg("ERROR", message);
    }

    private void printMsg(String type, Object message){

        String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
        String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
        String fileName = className+ ".java";
        String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
        int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();

        System.out.println("["+type+"] At " + className + "." + methodName
                + "(" + fileName + ":" + lineNumber + ") : " + message);
    }

}
