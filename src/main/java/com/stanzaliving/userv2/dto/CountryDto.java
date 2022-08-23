/**
 * 
 */
package com.stanzaliving.userv2.dto;

import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CountryDto {

	private String countryName;

	private int countryCode;

	private String isoCode;

}