    public boolean isBeforeFirst() throws SQLException {

        checkClosed();

        if (isOnInsertRow) {
            return false;
        }

        return navigator.isBeforeFirst();
    }
