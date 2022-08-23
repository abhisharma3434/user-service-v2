package com.stanzaliving.userv2.utils;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberType;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.stanzaliving.userv2.constants.StanzaConstants;
import com.stanzaliving.userv2.dto.CountryDto;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

@Log4j2
@UtilityClass
public class PhoneNumberUtils {

	private static PhoneNumberUtil util = PhoneNumberUtil.getInstance();

	public static PhoneNumberUtil getPhoneNumberUtil() {
		return util;
	}

	public static boolean isValidMobileForCountry(String mobile, String isoCode) {
		
		if (StringUtils.isBlank(mobile) || StringUtils.isBlank(isoCode)) {
			return false;
		}

		if(mobile.startsWith("2")) {
			return true;
		}
		
		try {
			PhoneNumber ph = util.parse(normalizeNumber(mobile), isoCode);
			return util.isValidNumber(ph) && isPhoneMobile(ph);

		} catch (Exception e) {
			log.error("Invalid number encountered while validating number: " + mobile + " for country: " + isoCode, e);
		}

		return false;
	}

	private boolean isPhoneMobile(PhoneNumber phonenumber) {

		PhoneNumberType phoneNumberType = util.getNumberType(phonenumber);

		return PhoneNumberType.MOBILE == phoneNumberType || PhoneNumberType.FIXED_LINE_OR_MOBILE == phoneNumberType;
	}

	public static List<CountryDto> getAllCountries() {

		List<CountryDto> countryDtos = new ArrayList<>();

		Set<String> set = util.getSupportedRegions();

		for (String region : set) {

			Locale locale = new Locale("en", region);

			countryDtos.add(
					new CountryDto(locale.getDisplayCountry(), util.getCountryCodeForRegion(region), locale.getCountry()));
		}

		return countryDtos;
	}

	public static Map<String, Integer> getAllCountryCode() {

		Set<String> set = util.getSupportedRegions();
		String[] arr = set.toArray(new String[set.size()]);
		Map<String, Integer> countryVsCode = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			Locale locale = new Locale("en", arr[i]);
			countryVsCode.put(locale.getDisplayCountry(), util.getCountryCodeForRegion(arr[i]));
		}

		return countryVsCode;
	}

	public static String normalizeNumber(String number) {

		if (StringUtils.isNotBlank(number)) {
			number = PhoneNumberUtil.normalizeDigitsOnly(number);
		}

		return number;
	}

	public static PhoneNumber getValidMobile(String phone) {
		return getValidMobileForRegion(phone, StanzaConstants.INDIA_ISO_CODE);
	}

	public static PhoneNumber getValidMobileForCountry(String phone, String countryCode) {
		return getValidMobileForRegion(phone, StringUtils.isNotBlank(countryCode) ? getIsoCodeForCountryCode(countryCode) : StanzaConstants.INDIA_ISO_CODE);
	}

	public static PhoneNumber getValidMobileForRegion(String phone, String isoCode) {

		if (StringUtils.isBlank(phone) || StringUtils.isBlank(isoCode)) {
			return null;
		}

		try {
			PhoneNumber phoneNumber = util.parse(normalizeNumber(phone), isoCode);

			boolean possibleMobile = util.isPossibleNumberForType(phoneNumber, PhoneNumberType.MOBILE);

			if (possibleMobile && isPhoneMobile(phoneNumber)) {
				return phoneNumber;
			}

		} catch (Exception e) {
			log.error("Invalid number encountered while validating number: " + phone + " for Region: " + isoCode, e);
		}

		return null;
	}

	public static String getIsoCodeForCountryCode(String countryCode) {

		String isoCode = util.getRegionCodeForCountryCode(Integer.valueOf(PhoneNumberUtil.normalizeDigitsOnly(countryCode)));

		if (StringUtils.isBlank(isoCode)) {
			isoCode = StanzaConstants.INDIA_ISO_CODE;
		}

		return isoCode;
	}

	public static Integer getCountryCodeForIsoCode(String isoCode) {

		int countryCode = util.getCountryCodeForRegion(isoCode);

		return countryCode > 0 ? countryCode : null;
	}

}