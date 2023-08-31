package com.iot.pmonitor.constants;

public  final class SQLQueryConstants {

    SQLQueryConstants(){}
public static  final String ALL_PRODUCTION_MONITOR = "select MACH_ID, MACH_NAME, MACH_STATUS, PART_ID, PART_NAME, MACH_TARGET_JOB_COUNT,MACH_COMPLETED_JOB_COUNT from production_monitor";
}
