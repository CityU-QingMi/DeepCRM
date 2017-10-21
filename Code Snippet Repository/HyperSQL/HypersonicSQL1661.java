    public java.io.InputStream getBinaryStream(
            int columnIndex) throws SQLException {

        checkColumn(columnIndex);

        Type   sourceType = resultMetaData.columnTypes[columnIndex - 1];
        Object o          = getColumnInType(columnIndex, sourceType);

        if (o == null) {
            return null;
        }

        if (o instanceof BlobDataID) {
            return ((BlobDataID) o).getBinaryStream(session);
        } else if (o instanceof Blob) {
            return ((Blob) o).getBinaryStream();
        } else if (o instanceof BinaryData) {
            byte[] b = getBytes(columnIndex);

            return new ByteArrayInputStream(b);
        }

        throw JDBCUtil.sqlException(ErrorCode.X_42561);
    }
