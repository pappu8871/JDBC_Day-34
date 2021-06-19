package com.practice.jdbc;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.practice.jdbc.EmployeePayrollService.IOService;

class EmployeePayrollServiceTest {
	private IOService fileIOService = IOService.FILE_IO;

	@Test
	public void given3EmployeesWhenWrittenToFileShouldMatchEmployeeEntries() {
		EmployeePayrollData[] arrayOfEmps = {
				new EmployeePayrollData(1, "Bill", 10000.0),
				new EmployeePayrollData(2, "Teria", 20000.0),
				new EmployeePayrollData(3, "Charlie", 30000.0),
		};

		EmployeePayrollService employeePayrollService = new EmployeePayrollService(Arrays.asList(arrayOfEmps));
		employeePayrollService.writeEmployeePayrollData(fileIOService);
		employeePayrollService.printData(fileIOService);
		long entries = employeePayrollService.countEntries(fileIOService);
		//	Assert.assertEquals(3, entries);
		  Assert.assertEquals(3, entries);
	}

	@Test
	public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount() {
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(IOService.DB_IO);
		Assertrtion.assertEquals(3, employeePayrollData.size());

	}

	@Test
	public void givenNewSalaryForEmployee_WhenUpdatedShouldSyncWithDB() {
		EmployeePayrollService employeePayrollService = new EmployeePayrollService ();
		List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(fileIOService);
		employeePayrollService.updateEmployeeSalary("Teria", 30000.0);
			boolean result = employeePayrollService.checkEmployeePayrollInSynWithDB("Teria");
			Assert.assertTrue(result);

	}
}
