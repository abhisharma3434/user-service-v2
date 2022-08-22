package com.stanzaliving.aclv2.dto;

import com.stanzaliving.location.enums.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDetailsDto {

	private String locationName;

	private LocationType locationType;

	private Shape shape;

//    private String locationPolygon;

	private LocationStatus locationStatus;

	private String address;

	private String locationId;

//    private String geographicalCoordinates;

//    private String serviceableAreaPolygon;

	private Date createdAt;

	private String createdBy;

	private Date updatedAt;

	private String updatedBy;

	private String parentLocationId;

	private String parentLocationName;

}