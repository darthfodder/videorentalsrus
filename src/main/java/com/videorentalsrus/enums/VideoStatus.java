package com.videorentalsrus.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum VideoStatus {
	ACTIVE,DECOMMISSIONED,UNKNOWN;
	
	@JsonValue
	public String getValue()
	{
		return this.toString();
	}
	
	@JsonCreator
	public static VideoStatus forValue(String value)
	{
		return Enum.valueOf(VideoStatus.class, value);
	}
}
