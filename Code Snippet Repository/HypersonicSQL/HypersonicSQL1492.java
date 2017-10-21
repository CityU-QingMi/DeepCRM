    public synchronized Blob getBlob(int parameterIndex) throws SQLException {

        checkGetParameterIndex(parameterIndex);

        Type   sourceType = parameterMetaData.columnTypes[parameterIndex - 1];
        Object o          = getColumnInType(parameterIndex, sourceType);

        if (o == null) {
            return null;
        }

        if (o instanceof BlobDataID) {
            return new JDBCBlobClient(session, (BlobDataID) o);
        }

        throw JDBCUtil.sqlException(ErrorCode.X_42561);
    }
