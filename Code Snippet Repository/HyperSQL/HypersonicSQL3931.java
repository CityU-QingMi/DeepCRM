    Object convertColumnValue(Object value, int column, int type) {

        // solves a problem for MS SQL 7
        if ((type == Types.SMALLINT) && (value instanceof Integer)) {
            if (firstSmallintRow) {
                firstSmallintRow = false;

                tracer.trace("SMALLINT: Converted column " + column
                             + " Integer to Short");
            }

            value = new Short((short) ((Integer) value).intValue());
        } else if ((type == Types.TINYINT) && (value instanceof Integer)) {
            if (firstTinyintRow) {
                firstTinyintRow = false;

                tracer.trace("TINYINT: Converted column " + column
                             + " Integer to Byte");
            }

            value = new Byte((byte) ((Integer) value).intValue());
        }

        return (value);
    }
