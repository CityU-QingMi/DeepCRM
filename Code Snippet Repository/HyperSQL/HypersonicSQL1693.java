    public byte[] getBytes(int columnIndex) throws SQLException {

        checkColumn(columnIndex);

        Type sourceType = resultMetaData.columnTypes[columnIndex - 1];

        if (sourceType.typeCode == Types.SQL_BLOB) {
            BlobDataID x = (BlobDataID) getColumnInType(columnIndex,
                sourceType);

            if (x == null) {
                return null;
            }

            long length = x.length(session);

            if (length > Integer.MAX_VALUE) {
                JDBCUtil.throwError(Error.error(ErrorCode.X_42561));
            }

            return x.getBytes(session, 0, (int) length);
        }

        Object x = getColumnInType(columnIndex, Type.SQL_VARBINARY);

        if (x == null) {
            return null;
        }

        return ((BinaryData) x).getBytes();
    }
