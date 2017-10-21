    public Blob getBlob(int columnIndex) throws SQLException {

        checkColumn(columnIndex);

        Type   sourceType = resultMetaData.columnTypes[columnIndex - 1];
        Object o          = getColumnInType(columnIndex, sourceType);

        if (o == null) {
            return null;
        }

        if (o instanceof BlobDataID) {
            JDBCBlobClient blob = new JDBCBlobClient(session, (BlobDataID) o);

            if (isUpdatable) {
                if (resultMetaData.colIndexes[columnIndex - 1] > 0
                        && resultMetaData.columns[columnIndex - 1]
                            .isWriteable()) {
                    blob.setWritable(this, columnIndex - 1);
                }
            }

            return blob;
        } else if (o instanceof Blob) {
            return (Blob) o;
        } else if (o instanceof BinaryData) {
            byte[] b = getBytes(columnIndex);

            return new JDBCBlob(b);
        }

        throw JDBCUtil.sqlException(ErrorCode.X_42561);
    }
