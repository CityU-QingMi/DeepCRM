    public boolean isExactNumberType() {

        switch (typeCode) {

            case Types.SQL_REAL :
            case Types.SQL_FLOAT :
            case Types.SQL_DOUBLE :
                return false;

            default :
                return true;
        }
    }
