    private void runStatements() throws SQLException {

        Statement st = conn.createStatement();

        st.execute("delete from testtrig");
        st.execute("alter table testtrig alter column c1 restart with 0");
        clearCalls();
        st.execute(
            "insert into testtrig values (default, 'inserted val 1', 100)");
        checkCallCount(3);
        checkCalls(Trigger.INSERT_AFTER, 1);
        checkCalls(Trigger.INSERT_BEFORE_ROW, 1);
        checkCalls(Trigger.INSERT_AFTER_ROW, 1);
        clearCalls();
        st.execute(
            "insert into testtrig (c2, c3) select c2, c3 from testtrig where c1 < 0");
        checkCallCount(1);
        checkCalls(Trigger.INSERT_AFTER, 1);
        checkCalls(Trigger.INSERT_BEFORE_ROW, 0);
        checkCalls(Trigger.INSERT_AFTER_ROW, 0);
        clearCalls();
        st.execute("update testtrig set c2 = c2 || ' updated' where c1 = 0");
        checkCallCount(3);
        checkCalls(Trigger.UPDATE_AFTER, 1);
        checkCalls(Trigger.UPDATE_BEFORE_ROW, 1);
        checkCalls(Trigger.UPDATE_AFTER_ROW, 1);
        clearCalls();
        st.execute("update testtrig set c2 = c2 || ' updated' where c1 < 0");
        checkCallCount(1);
        checkCalls(Trigger.UPDATE_AFTER, 1);
        checkCalls(Trigger.UPDATE_BEFORE_ROW, 0);
        checkCalls(Trigger.UPDATE_AFTER_ROW, 0);
        st.close();
    }
