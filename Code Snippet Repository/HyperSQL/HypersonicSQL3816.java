    public static boolean requiresPrecision(int type) {

        switch (type) {

            case Types.SQL_BIT_VARYING :
            case Types.SQL_VARBINARY :
            case Types.SQL_VARCHAR :
            case Types.SQL_NVARCHAR :
                return true;

            default :
                return false;
        }
    }
