    public void setTransactionIsolation(int level) throws SQLException {
        int currentLevel = conn.getTransactionIsolation();
        
        if (level != currentLevel) {
            if (overwroteOriginalTxIsolationValue == false) {
                overwroteOriginalTxIsolationValue = true;
                originalTxIsolationValue = currentLevel;
            }
            
            conn.setTransactionIsolation(level);
        }
    }
