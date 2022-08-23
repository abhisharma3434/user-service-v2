package com.stanzaliving.userv2.adapters;

import com.stanzaliving.userv2.enums.Department;
import com.stanzaliving.userv2.enums.EnumListing;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

/**
 * @author piyush srivastava "piyush@stanzaliving.com"
 *
 * @date 16-Apr-2020
 *
 */
@UtilityClass
public class DepartmentAdapter {

	public List<EnumListing<Department>> getDepartmentEnumAsEnumListing() {

		List<EnumListing<Department>> data = new ArrayList<>();

		for (Department department : Department.values()) {
			data.add(EnumListing.of(department, department.getDepartmentName()));
		}
		
		return data;
	}
}