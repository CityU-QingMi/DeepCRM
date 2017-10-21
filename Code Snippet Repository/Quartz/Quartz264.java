    public void setAutoCommit(boolean autoCommit) throws SQLException {
        boolean currentAutoCommitValue = conn.getAutoCommit();
            
        if (autoCommit != currentAutoCommitValue) {
            if (overwroteOriginalAutoCommitValue == false) {
                overwroteOriginalAutoCommitValue = true;
                originalAutoCommitValue = currentAutoCommitValue;
            }
            
            conn.setAutoCommit(autoCommit);
        }
    }
