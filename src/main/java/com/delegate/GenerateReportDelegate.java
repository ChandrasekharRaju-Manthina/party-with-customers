package com.delegate;

import com.domain.Customer;
import com.service.CustomerService;
import com.util.FileUtils;
import com.util.JsonUtils;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class GenerateReportDelegate {

    private static final Logger logger = Logger.getLogger(GenerateReportDelegate.class.getName());

    public void generateReport(String customersListFilePath, String outputReportFilePath) {
        logger.info("Reading file...");
        List<String> lines = FileUtils.readFile(customersListFilePath);

        logger.info("Parsing data...");
        List<Customer> customers = lines.stream()
                .map(line -> JsonUtils.readValue(line, Customer.class))
                .collect(Collectors.toList());

        logger.info("Processing data...");
        List<Customer> customerWithIn100KiloMeters = CustomerService.filterCustomersWithInKiloMeters(
                customers, 100L, "userid");

        logger.info("generating report...");
        lines = customerWithIn100KiloMeters.stream()
                .map(Customer::toString)
                .collect(Collectors.toList());
        FileUtils.writeToFile(lines, outputReportFilePath);
    }
}
