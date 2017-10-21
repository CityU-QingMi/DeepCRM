    private void resetDatabaseData() throws Exception {
        Connection conn = DBConnectionManager.getInstance().getConnection(DB_NAME);
        Statement statement = conn.createStatement();
        statement.addBatch("delete from qrtz_fired_triggers");
        statement.addBatch("delete from qrtz_paused_trigger_grps");
        statement.addBatch("delete from qrtz_scheduler_state");
        statement.addBatch("delete from qrtz_locks");
        statement.addBatch("delete from qrtz_simple_triggers");
        statement.addBatch("delete from qrtz_simprop_triggers");
        statement.addBatch("delete from qrtz_blob_triggers");
        statement.addBatch("delete from qrtz_cron_triggers");
        statement.addBatch("delete from qrtz_triggers");
        statement.addBatch("delete from qrtz_job_details");
        statement.addBatch("delete from qrtz_calendars");
        statement.executeBatch();
        statement.close();
        conn.close();
    }
