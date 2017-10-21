    public static boolean acceptsZeroPrecision(int type) {

        switch (type) {

            case Types.SQL_TIME :
            case Types.SQL_TIMESTAMP :
                return true;

            default :
                return false;
        }
    }
