    public String getCursorName() throws SQLException {

        checkClosed();

        if (result == null) {
            return "";
        }

        return result.getMainString();
    }
