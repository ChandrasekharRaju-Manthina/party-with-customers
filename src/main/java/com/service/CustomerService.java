package com.service;

import com.domain.Customer;
import com.util.GeoUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerService {

    private static final double DUBLIN_OFFICE_LATITUDE = 53.339428;
    private static final double DUBLIN_OFFICE_LONGITUDE = -6.257664;

    public static List<Customer> filterCustomersWithInKiloMeters(List<Customer> customers, Long kiloMeters,
                                                                 String sortBy) {
        List<Customer> customersWithInGivenKiloMeters = customers.stream()
                .filter(customer -> {
                    double distance = GeoUtils.distanceInKm(customer.getLatitude().doubleValue(),
                            customer.getLongitude().doubleValue(), DUBLIN_OFFICE_LATITUDE, DUBLIN_OFFICE_LONGITUDE);
                    return distance >= kiloMeters;
                })
                .collect(Collectors.toList());
        sortCustomers(sortBy, customersWithInGivenKiloMeters);
        return customersWithInGivenKiloMeters;
    }

    private static void sortCustomers(String sortBy, List<Customer> customersWithInGivenKiloMeters) {
        Comparator<Customer> comparator;
        switch (sortBy) {
            case "name":
                comparator = Comparator.comparing(Customer::getName);
                break;
            default:
                comparator = Comparator.comparingLong(Customer::getUser_id);

        }
        customersWithInGivenKiloMeters.sort(comparator);
    }
}
