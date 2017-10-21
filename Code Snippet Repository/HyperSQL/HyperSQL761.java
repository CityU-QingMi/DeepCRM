    public Array getArray(int columnIndex) throws SQLException {

        checkColumn(columnIndex);

        Type     type = resultMetaData.columnTypes[columnIndex - 1];
        Object[] data = (Object[]) getCurrent()[columnIndex - 1];

        if (!type.isArrayType()) {
            throw JDBCUtil.sqlException(ErrorCode.X_42561);
        }

        if (trackNull(data)) {
            return null;
        }

        return new JDBCArray(data, type.collectionBaseType(), type,
                             connection);
    }
