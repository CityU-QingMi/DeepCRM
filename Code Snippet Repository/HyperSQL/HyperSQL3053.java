    public boolean acceptsScale() {

        switch (typeCode) {

            case Types.SQL_DECIMAL :
            case Types.SQL_NUMERIC :
                return true;

            default :
                return false;
        }
    }
