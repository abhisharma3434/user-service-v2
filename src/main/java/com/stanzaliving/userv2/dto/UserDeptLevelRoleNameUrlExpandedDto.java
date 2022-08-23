package com.stanzaliving.userv2.dto;

import com.stanzaliving.userv2.enums.Department;
import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDeptLevelRoleNameUrlExpandedDto {

    private String userUuid;

    private Department department;

    private AccessLevel accessLevel;

    private List<String> accessLevelEntityListUuid;

    private Map<String, String> accessLevelEntityNameUuidMap;

    private List<String> rolesList;

    private Map<String, String> roleNameUuidMap;

    private List<String> urlList;

}
