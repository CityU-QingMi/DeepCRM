    public int getRow() throws SQLException {

        checkClosed();

        if (navigator.isAfterLast()) {
            return 0;
        }

        return navigator.getRowNumber() + 1;
    }
