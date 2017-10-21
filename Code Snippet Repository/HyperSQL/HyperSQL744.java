    public boolean isFirst() throws SQLException {

        checkClosed();

        if (isOnInsertRow) {
            return false;
        }

        return navigator.isFirst();
    }
