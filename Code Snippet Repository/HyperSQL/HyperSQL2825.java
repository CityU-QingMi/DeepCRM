    public static BinaryType getBinaryType(int type, long precision) {

        switch (type) {

            case Types.SQL_BINARY :
            case Types.SQL_VARBINARY :
                return new BinaryType(type, precision);

            case Types.SQL_BLOB :
                return new BlobType(precision);

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "BinaryType");
        }
    }
