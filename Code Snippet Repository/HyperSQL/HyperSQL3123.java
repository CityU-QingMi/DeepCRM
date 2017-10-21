    public static int getJDBCTypeCode(int type) {

        switch (type) {

            case Types.SQL_BLOB :
                return Types.BLOB;

            case Types.SQL_CLOB :
                return Types.CLOB;

            case Types.SQL_BIGINT :
                return Types.BIGINT;

            case Types.SQL_BINARY :
                return Types.BINARY;

            case Types.SQL_VARBINARY :
                return Types.VARBINARY;

            case Types.SQL_BIT :
            case Types.SQL_BIT_VARYING :
                return Types.BIT;

            case Types.SQL_ARRAY :
                return Types.ARRAY;

            default :
                return type;
        }
    }
