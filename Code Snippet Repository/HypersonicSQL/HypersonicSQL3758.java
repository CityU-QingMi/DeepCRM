    public String convertToString(Object a) {

        if (a == null) {
            return null;
        }

        switch (this.typeCode) {

            case Types.TINYINT :
            case Types.SQL_SMALLINT :
            case Types.SQL_INTEGER :
            case Types.SQL_BIGINT :
                return a.toString();

            case Types.SQL_REAL :
            case Types.SQL_DOUBLE :
                double value = ((Double) a).doubleValue();

                /** @todo - java 5 format change */
                if (value == Double.NEGATIVE_INFINITY) {
                    return "-1E0/0";
                }

                if (value == Double.POSITIVE_INFINITY) {
                    return "1E0/0";
                }

                if (Double.isNaN(value)) {
                    return "0E0/0E0";
                }

                String s = Double.toString(value);

                // ensure the engine treats the value as a DOUBLE, not DECIMAL
                if (s.indexOf('E') < 0) {
                    s = s.concat("E0");
                }

                return s;

            case Types.SQL_NUMERIC :
            case Types.SQL_DECIMAL :
                return JavaSystem.toString((BigDecimal) a);

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "NumberType");
        }
    }
