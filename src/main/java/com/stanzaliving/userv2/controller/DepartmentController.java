package com.stanzaliving.userv2.controller;

import com.stanzaliving.userv2.adapters.DepartmentAdapter;
import com.stanzaliving.userv2.dto.ResponseDto;
import com.stanzaliving.userv2.enums.Department;
import com.stanzaliving.userv2.enums.EnumListing;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Log4j2
@RestController
@RequestMapping("department")
public class DepartmentController {

	@GetMapping("list")
	public ResponseDto<List<EnumListing<Department>>> getUserDepartment() {

		log.info("Received Department listing request.");
		return ResponseDto.success("Found Department List", DepartmentAdapter.getDepartmentEnumAsEnumListing());
	}
}
