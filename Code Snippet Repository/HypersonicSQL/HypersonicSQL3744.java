    public int getMaxScale() {

        switch (typeCode) {

            case Types.SQL_NUMERIC :
            case Types.SQL_DECIMAL :
                return Short.MAX_VALUE;

            default :
                return 0;
        }
    }
