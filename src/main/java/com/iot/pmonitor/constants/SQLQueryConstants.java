package com.iot.pmonitor.constants;

public final class SQLQueryConstants {

    SQLQueryConstants() {
    }

    public static final String LIVE_PRODUCTION_MONITOR = "select MACH_ID, MACH_NAME, MACH_STATUS, PART_ID, PART_NAME, MACH_TARGET_JOB_COUNT,MACH_COMPLETED_JOB_COUNT from production_monitor";

    //for Monitor Id
    public static final String PM_BY_MONITOR_ID = "select pma.mach_id, mach.mach_name, mach.MACH_IP_ADDRESS,mach.MACH_PORT_NO,mach.MACH_PLC_TYPE, pma.part_id, part.part_name,pma.mach_target_job_count, pma.mach_completed_job_count,pma.MACH_STATUS,pma.MACH_JOB_STATUS, pma.crte_ts,pma.lst_updt_ts from production_monitor_audit pma, machine mach, part part where pma.part_id=part.part_id and pma.mach_id=mach.mach_id and pma.status_cd = 'A' and date(pma.crte_ts) >=date(:fromDate) and date(pma.lst_updt_ts) <= date(:toDate) and pma.mach_id = coalesce(:machineId, pma.mach_id)  order by :sortName asc limit :pageSize offset :pageOffset";
    public static final String COUNT_BY_MONITOR_ID = "select count(*) from production_monitor_audit pma, machine mach, part part where pma.part_id=part.part_id and pma.mach_id=mach.mach_id and pma.MACH_STATUS = 'A' and date(pma.crte_ts) >=date(:fromDate) and date(pma.lst_updt_ts) <= date(:toDate) and pma.mach_id = coalesce(:machineId, pma.mach_id)";


    //for Part Id
    public static final String PM_BY_PART_ID = "select pma.mach_id, mach.mach_name, mach.MACH_IP_ADDRESS,mach.MACH_PORT_NO,mach.MACH_PLC_TYPE, pma.part_id, part.part_name,pma.mach_target_job_count, pma.mach_completed_job_count,pma.MACH_STATUS,pma.MACH_JOB_STATUS, pma.crte_ts,pma.lst_updt_ts from production_monitor_audit pma, machine mach, part part where pma.part_id=part.part_id and pma.mach_id=mach.mach_id and pma.status_cd = 'A' and date(pma.crte_ts) >=date(:fromDate) and date(pma.lst_updt_ts) <= date(:toDate) and pma.part_id = coalesce(:partId, pma.part_id)  order by :sortName asc limit :pageSize offset :pageOffset";
    public static final String COUNT_BY_PART_ID = "select count(*) from production_monitor_audit pma, machine mach, part part where pma.part_id=part.part_id and pma.mach_id=mach.mach_id and pma.MACH_STATUS = 'A' and date(pma.crte_ts) >=date(:fromDate) and date(pma.lst_updt_ts) <= date(:toDate) and pma.part_id = coalesce(:partId, pma.part_id)";

    //for BY JOB TARGET COUNT
    public static final String PM_BY_JOB_TARGET_COUNT = "select pma.mach_id, mach.mach_name, mach.MACH_IP_ADDRESS,mach.MACH_PORT_NO,mach.MACH_PLC_TYPE, pma.part_id, part.part_name,pma.mach_target_job_count, pma.mach_completed_job_count,pma.MACH_STATUS,pma.MACH_JOB_STATUS, pma.crte_ts,pma.lst_updt_ts from production_monitor_audit pma, machine mach, part part where pma.part_id=part.part_id and pma.mach_id=mach.mach_id and pma.status_cd = 'A' and date(pma.crte_ts) >=date(:fromDate) and date(pma.lst_updt_ts) <= date(:toDate) and pma.mach_target_job_count = coalesce(:machTargetJobCount, pma.mach_target_job_count)  order by :sortName asc limit :pageSize offset :pageOffset";
    public static final String COUNT_BY_JOB_TARGET_COUNT = "select count(*) from production_monitor_audit pma, machine mach, part part where pma.part_id=part.part_id and pma.mach_id=mach.mach_id and pma.MACH_STATUS = 'A' and date(pma.crte_ts) >=date(:fromDate) and date(pma.lst_updt_ts) <= date(:toDate) and pma.mach_target_job_count = coalesce(:machTargetJobCount, pma.mach_target_job_count) ";

    //for BY JOB TARGET COUNT
    public static final String PM_BY_MACH_JOB_STATUS = "select pma.mach_id, mach.mach_name, mach.MACH_IP_ADDRESS,mach.MACH_PORT_NO,mach.MACH_PLC_TYPE, pma.part_id, part.part_name,pma.mach_target_job_count, pma.mach_completed_job_count,pma.MACH_STATUS,pma.MACH_JOB_STATUS, pma.crte_ts,pma.lst_updt_ts from production_monitor_audit pma, machine mach, part part where pma.part_id=part.part_id and pma.mach_id=mach.mach_id and pma.status_cd = 'A' and date(pma.crte_ts) >=date(:fromDate) and date(pma.lst_updt_ts) <= date(:toDate) and pma.MACH_JOB_STATUS = coalesce(:machJobStatus, pma.MACH_JOB_STATUS)  order by :sortName asc limit :pageSize offset :pageOffset";
    public static final String COUNT_BY_MACH_JOB_STATUS = "select count(*) from production_monitor_audit pma, machine mach, part part where pma.part_id=part.part_id and pma.mach_id=mach.mach_id and pma.MACH_STATUS = 'A' and date(pma.crte_ts) >=date(:fromDate) and date(pma.lst_updt_ts) <= date(:toDate) and pma.MACH_JOB_STATUS = coalesce(:machJobStatus, pma.MACH_JOB_STATUS) ";

    //for all

    public static final String PM_BY_ALL = "select pma.mach_id, mach.mach_name, mach.MACH_IP_ADDRESS,mach.MACH_PORT_NO,mach.MACH_PLC_TYPE, pma.part_id, part.part_name,pma.mach_target_job_count, pma.mach_completed_job_count,pma.MACH_STATUS,pma.MACH_JOB_STATUS, pma.crte_ts,pma.lst_updt_ts from production_monitor_audit pma, machine mach, part part where pma.part_id=part.part_id and pma.mach_id=mach.mach_id and pma.status_cd = 'A' and date(pma.crte_ts) >=date(:fromDate) and date(pma.lst_updt_ts) <= date(:toDate) order by :sortName asc limit :pageSize offset :pageOffset";
    public static final String COUNT_BY_ALL = "select count(*) from production_monitor_audit pma, machine mach, part part where pma.part_id=part.part_id and pma.mach_id=mach.mach_id and pma.MACH_STATUS = 'A' and date(pma.crte_ts) >=date(:fromDate) and date(pma.lst_updt_ts) <= date(:toDate)";

    public static final String UPDATE_ASSIGNED_JOB_PART = "update part set PART_JOB_ASSIGNED = :totalPartJobAssigned where PART_ID = :partId";


}
