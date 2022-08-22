package com.stanzaliving.aclv2.enums;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BloodGroup {

    O_POSITIVE("O+"),
    O_NEGATIVE("O-"),
    A_POSITIVE("A+"),
    A_NEGATIVE("A-"),
    B_POSITIVE("B+"),
    B_NEGATIVE("B-"),
    AB_POSITIVE("AB+"),
    AB_NEGATIVE("AB-");

    private String name;

    private static List<EnumListing<BloodGroup>> bloodGroups = new ArrayList<>();

    static {
        for (BloodGroup bloodGroup : BloodGroup.values()) {
            bloodGroups.add(EnumListing.of(bloodGroup, bloodGroup.getName()));
        }
    }

    public static List<EnumListing<BloodGroup>> getBloodGroups() {
        return bloodGroups;
    }
}
