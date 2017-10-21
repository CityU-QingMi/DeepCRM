    public boolean isLast() throws SQLException {

        checkClosed();

        if (isOnInsertRow) {
            return false;
        }

        return navigator.isLast();
    }
