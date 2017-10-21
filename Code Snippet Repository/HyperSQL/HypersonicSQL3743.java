    public long getMaxPrecision() {

        switch (typeCode) {

            case Types.SQL_NUMERIC :
            case Types.SQL_DECIMAL :
                return maxNumericPrecision;

            default :
                return getNumericPrecisionInRadix();
        }
    }
