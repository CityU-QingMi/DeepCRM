    public boolean isIntegralType() {

        switch (typeCode) {

            case Types.SQL_REAL :
            case Types.SQL_FLOAT :
            case Types.SQL_DOUBLE :
                return false;

            case Types.SQL_NUMERIC :
            case Types.SQL_DECIMAL :
                return scale == 0;

            default :
                return true;
        }
    }
