package com.masterdevskills.cha2.ext4;

import com.masterdevskills.cha2.ext2.model.Movie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author A N M Bazlur Rahman @bazlur_rahman
 * @since 08 August 2020
 */
public class HrService {

	/**
	 * TODO
	 * Having a list of employees, find all the departments where total employees
	 * salary exceeds $1,000,000; sort the result by salary in decreasing order.
	 *
	 * @param employees       list of employees
	 * @param salaryExceeding say  $1,000,000
	 * @return Map key -> department's name, value -> sum of all salaries
	 */

	public Map<String, Long> findAllDepartmentNameExceedingSalaries(List<? extends Employee> employees, long salaryExceeding) {

		return employees.stream()
				.collect(
						Collectors.collectingAndThen(
								Collectors.toMap(e -> e.getDepartment().getName(), Employee::getSalary, Math::addExact),
								map -> {
									map.values().removeIf(value -> value <= salaryExceeding);
									return map;
								}));

	}

}
