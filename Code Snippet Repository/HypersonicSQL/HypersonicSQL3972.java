    Object convertColumnValue(Object value, int column, int type) {

        if (value == null) {
            return value;
        }

        try {
            if (value instanceof Clob) {
                return ((Clob) value).getSubString(
                    1, (int) ((Clob) value).length());
            } else if (value instanceof Blob) {
                return ((Blob) value).getBytes(
                    1, (int) ((Blob) value).length());
            }
        } catch (SQLException e) {
            return null;
        }

        return (value);
    }
