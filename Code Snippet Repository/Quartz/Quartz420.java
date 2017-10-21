    public List<OperableTrigger> selectTriggersForCalendar(Connection conn, String calName)
        throws SQLException, ClassNotFoundException, IOException, JobPersistenceException {

        LinkedList<OperableTrigger> trigList = new LinkedList<OperableTrigger>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(rtp(SELECT_TRIGGERS_FOR_CALENDAR));
            ps.setString(1, calName);
            rs = ps.executeQuery();

            while (rs.next()) {
                trigList.add(selectTrigger(conn, triggerKey(rs.getString(COL_TRIGGER_NAME), rs.getString(COL_TRIGGER_GROUP))));
            }
        } finally {
            closeResultSet(rs);
            closeStatement(ps);
        }

        return trigList;
    }
