package com;

import com.delegate.GenerateReportDelegate;

public class Main {

    public static void main(String[] args) {
        if (args == null || args.length != 2) {
            throw new RuntimeException("Please pass input file and output file paths details");
        }
        GenerateReportDelegate generateReportDelegate = new GenerateReportDelegate();
        generateReportDelegate.generateReport(args[0], args[1]);
    }
}
