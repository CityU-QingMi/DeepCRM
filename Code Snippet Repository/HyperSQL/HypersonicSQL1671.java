    public String getString(int columnIndex) throws SQLException {

        checkColumn(columnIndex);

        Type sourceType = resultMetaData.columnTypes[columnIndex - 1];

        if (sourceType.typeCode == Types.SQL_CLOB) {
            ClobDataID x = (ClobDataID) getColumnInType(columnIndex,
                sourceType);

            if (x == null) {
                return null;
            }

            long length = x.length(session);

            if (length > Integer.MAX_VALUE) {
                JDBCUtil.throwError(Error.error(ErrorCode.X_42561));
            }

            return x.getSubString(session, 0, (int) length);
        }

        return (String) getColumnInType(columnIndex, Type.SQL_VARCHAR);
    }
