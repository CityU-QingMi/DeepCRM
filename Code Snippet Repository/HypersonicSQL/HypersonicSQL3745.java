    public boolean acceptsPrecision() {

        switch (typeCode) {

            case Types.SQL_DECIMAL :
            case Types.SQL_NUMERIC :
            case Types.SQL_FLOAT :
                return true;

            default :
                return false;
        }
    }
