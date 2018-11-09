package com.bluespace.tech.hrms.domain.member.details;

import java.util.Date;

import lombok.Data;

// IMP NOTE: This document (table in RDBMS terminology needs to be vetted out)
@Data
public class LeadPreferences {
	
    static public enum SmsOptInStatus
	{
		NOT_SENT,
        SENT,
        ACCEPTED,
        DECLINED
	}
    
	private boolean emailOptIn;
	private boolean smsOptIn;
	private boolean wantsHtml;
	private Date dateEmailOptedIn;
	private Date dateEmailOptedOut;
	private Date dateSmsOptedIn;
	private SmsOptInStatus smsOptInStatus = SmsOptInStatus.NOT_SENT ;
	private Date dateSmsOptedOut;
	private String ipAddressEmailOptIn;
	private String ipAddressEmailOptOut;

}
