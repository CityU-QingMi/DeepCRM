    public static boolean isSearchable(int type) {

        switch (type) {

            case Types.SQL_BLOB :
            case Types.SQL_CLOB :
            case Types.NCLOB :
            case Types.JAVA_OBJECT :
            case Types.STRUCT :
            case Types.OTHER :
            case Types.ROWID :
                return false;

            case Types.SQL_ARRAY :
            default :
                return true;
        }
    }
