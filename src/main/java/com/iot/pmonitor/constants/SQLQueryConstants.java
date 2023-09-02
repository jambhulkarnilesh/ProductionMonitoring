package com.iot.pmonitor.constants;

public final class SQLQueryConstants {

    SQLQueryConstants() {
    }

    public static final String LIVE_PRODUCTION_MONITOR = "select MACH_ID, MACH_NAME, MACH_STATUS, PART_ID, PART_NAME, MACH_TARGET_JOB_COUNT,MACH_COMPLETED_JOB_COUNT from production_monitor";

    public static final String PRODUCTION_MONITOR = "select pma.mach_id, mach.mach_name, mach.MACH_IP_ADDRESS,mach.MACH_PORT_NO,mach.MACH_PLC_TYPE, pma.part_id, part.part_name,pma.mach_target_job_count, pma.mach_completed_job_count,pma.MACH_STATUS,pma.MACH_JOB_STATUS, pma.crte_ts,pma.lst_updt_ts from production_monitor_audit pma, machine mach, part part where pma.part_id=part.part_id and pma.mach_id=mach.mach_id and date(pma.crte_ts) >=date(:fromDate) and date(pma.lst_updt_ts) <= date(:toDate) and pma.mach_id = coalesce(:machineId, pma.mach_id) and mach.mach_name = coalesce(:machineName, mach.mach_name) and mach.MACH_PLC_TYPE = coalesce(:machinePLCType, mach.MACH_PLC_TYPE) and pma.part_id = coalesce(:partId, pma.part_id) and part.part_name = coalesce(:partName, part.part_name) and pma.mach_target_job_count = coalesce(:machTargetJobCount, pma.mach_target_job_count) and pma.mach_completed_job_count = coalesce(:machCompletedJobCount, pma.mach_completed_job_count) and pma.MACH_STATUS = coalesce(:machineStatus, pma.MACH_STATUS) and pma.MACH_JOB_STATUS = coalesce(:machJobStatus, pma.MACH_JOB_STATUS) order by :sortName asc limit :pageSize offset :pageOffset";

    public static final String COUNT_PRODUCTION_MONITOR = "select count(*) from production_monitor_audit pma, machine mach, part part where pma.part_id=part.part_id and pma.mach_id=mach.mach_id and date(pma.crte_ts) >=date(:fromDate) and date(pma.lst_updt_ts) <= date(:toDate) and pma.mach_id = coalesce(:machineId, pma.mach_id) and mach.mach_name = coalesce(:machineName, mach.mach_name) and mach.MACH_PLC_TYPE = coalesce(:machinePLCType, mach.MACH_PLC_TYPE) and pma.part_id = coalesce(:partId, pma.part_id) and part.part_name = coalesce(:partName, part.part_name) and pma.mach_target_job_count = coalesce(:machTargetJobCount, pma.mach_target_job_count) and pma.mach_completed_job_count = coalesce(:machCompletedJobCount, pma.mach_completed_job_count) and pma.MACH_STATUS = coalesce(:machineStatus, pma.MACH_STATUS) and pma.MACH_JOB_STATUS = coalesce(:machJobStatus, pma.MACH_JOB_STATUS)";

    public static  final String UPDATE_ASSIGNED_JOB_PART = "update part set PART_JOB_ASSIGNED = :totalPartJobAssigned where PART_ID = :partId";


}
