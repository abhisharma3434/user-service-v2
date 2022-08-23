/**
 *
 */
package com.stanzaliving.userv2.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.format.DateTimeFormatter;


@Getter
@AllArgsConstructor
public enum DateFormat {
  
    YYYY_HIFEN_MM_HIFEN_DD("yyyy-MM-dd"),
    YYYY_MM_DD("yyyyMMdd"),
    ELASTIC_SEARCH("yyyy-MM-dd HH:mm:ss"),
    COMMENT_FORMAT("dd/MM/yyyy (HH:mm)"),
    COMMENT_FORMAT1("dd MMM, yyyy (HH:mm)"),
    MONTH_FULL_NAME("MMMM"),
    DD_MMM("dd MMM"),
    MMM("MMM"),
    DD_MMM_YY("dd MMM''YY"),
    DD_MMM_YY2("dd MMM YY"),
    DD_MMM_YY3("dd MMM''yy"),
    DD_MMM_YY_HH_MM("dd MMM''YY HH:mm"),
    D_DD_MMM_YY("EEE, dd MMM''yy"),
    WEEK_OF_YEAR("w"),
    YEAR_IN_WEEK_OF_YEAR("YYYY"),
    DAY_OF_MONTH("d"),
    DD_MMM_YYYY("dd-MMM-yyyy"),
    DATE_TIME_TIME_ZONE("yyyy-MM-dd'T'HH:mm:ss'Z'"),
    DD_SPACE_MMM_SPACE_YYYY("dd MMM yyyy"),
    DDMMMYYYY("ddMMMyyyy"),
    ELASTIC_SEARCH_WITH_MILI_SECONDS("yyyy-MM-dd HH:mm:ss.S"),
    MMMM_YYYY("MMMM, yyyy"),//June, 2020
    DD_MMMM_YYYY("dd MMMM, yyyy"),
    MMM_YYYY("MMM, yyyy"),
    MMM_YY("MMM, yy"),
    MMM_YY2("MMM''yy"),
    DD_MM_YYYY("dd/MM/yyyy"),
    DD_MMM_YYYY_H_MM_A("dd MMM yyyy h:mm a"),
    DD_MMM_YY_H_MM_A1("dd MMM''YY, h:mm a"),
    H_MM_A("h:mm a"),
    FRESHDESK_DATE_FORMAT("yyyy-MM-dd'T'HH:mm:ssXXX"),
    YYYY_MM_DD_T_HH_MM_SSXXX("yyyy-MM-dd'T'HH:mm:ssXXX"),
    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd_HH-mm-ss"),
    E_MMM_DD_HH_MM_SS_Z_YYYY("E MMM dd HH:mm:ss Z yyyy"),
    DATE_TIME_TIME_SSS_ZONE("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
    FILIX_ORACLE_FORMAT("dd/MM/YYYY"),
    ZOHO_DATE_COMMENT_FORMAT("dd MMM, yyyy (HH:mm)"),
    YYYY_SLASH_MM_SLASH_DD("yyyy/MM/dd");

    private String value;

    public DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(this.value);
    }

}