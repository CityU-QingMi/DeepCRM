    public static boolean acceptsScaleCreateParam(int type) {

        switch (type) {

            case Types.SQL_INTERVAL_SECOND :
                return true;

            case Types.SQL_DECIMAL :
            case Types.SQL_NUMERIC :
                return true;

            default :
                return false;
        }
    }
