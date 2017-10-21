    public boolean isAfterLast() throws SQLException {

        // At afterLast condition exists when resultset has been traversed and
        // the current row is null.  iCurrentRow should also be set to
        // afterlast but no need to test
        checkClosed();

        if (isOnInsertRow) {
            return false;
        }

        return navigator.isAfterLast();
    }
