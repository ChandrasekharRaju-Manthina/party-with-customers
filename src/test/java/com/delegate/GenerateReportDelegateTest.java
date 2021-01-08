package com.delegate;

import com.delegate.GenerateReportDelegate;
import com.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GenerateReportDelegateTest {

    @Test
    public void testGenerateReport() {
        // form input and output file paths
        String basedir = (String) System.getProperties().get("user.dir");
        String customersListFilePath = basedir + "/src/main/resources/customers.txt";
        String outputReportFilePath = basedir + "/report.csv";

        // generate report
        GenerateReportDelegate generateReportDelegate = new GenerateReportDelegate();
        generateReportDelegate.generateReport(customersListFilePath, outputReportFilePath);

        // check the file exists
        Path path = Paths.get(outputReportFilePath);
        Assert.assertTrue(Files.exists(path));

        // verify number of records
        List<String> customers = FileUtils.readFile(outputReportFilePath);
        Assert.assertEquals(16, customers.size());

        // verify that records are sorted by userid
        List<Long> userIds = customers.stream()
                .map(line -> Long.parseLong(line.split(",")[0]))
                .collect(Collectors.toList());
        Assert.assertTrue(isSorted(userIds));
    }

    public static boolean isSorted(List<Long> list) {
        return IntStream.range(0, list.size() - 1).noneMatch(i -> list.get(i) > list.get(i + 1));
    }

}