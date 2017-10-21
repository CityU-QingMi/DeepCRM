    public boolean isDecimalType() {

        switch (typeCode) {

            case Types.SQL_NUMERIC :
            case Types.SQL_DECIMAL :
                return true;

            default :
                return false;
        }
    }
