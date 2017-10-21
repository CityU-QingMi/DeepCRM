    public int getPrecision(int param) throws SQLException {

        checkRange(param);

        Type type = translateType(rmd.columnTypes[--param]);

        if (type.isDateTimeType()) {
            return type.displaySize();
        } else {
            long size = type.precision;

            if (size > Integer.MAX_VALUE) {
                size = 0;
            }

            return (int) size;
        }
    }
