    private Type getParameterType(Type type) {

        if (type == null) {
            return null;
        }

        switch (type.typeCode) {

            case Types.SQL_CHAR :
            case Types.SQL_VARCHAR :
                return Type.SQL_VARCHAR_DEFAULT;

            case Types.SQL_CLOB :
                return Type.SQL_CLOB;

            case Types.SQL_BINARY :
            case Types.SQL_VARBINARY :
                return Type.SQL_VARBINARY_DEFAULT;

            case Types.SQL_BLOB :
                return Type.SQL_BLOB;

            case Types.SQL_BIT :
            case Types.SQL_BIT_VARYING :
                return Type.SQL_BIT_VARYING_MAX_LENGTH;

            case Types.SQL_ARRAY :
                return type;

            default :
                return null;
        }
    }
