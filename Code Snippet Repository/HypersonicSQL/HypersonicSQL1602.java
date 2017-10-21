    public boolean supportsConvert(int fromType,
                                   int toType) throws SQLException {

        Type from =
            Type.getDefaultTypeWithSize(Type.getHSQLDBTypeCode(fromType));
        Type to = Type.getDefaultTypeWithSize(Type.getHSQLDBTypeCode(toType));

        if (from == null || to == null) {
            return false;
        }

        if (fromType == java.sql.Types.NULL
                && toType == java.sql.Types.ARRAY) {
            return true;
        }

        return to.canConvertFrom(from);
    }
