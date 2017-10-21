        public Blob getBlob(int columnIndex) throws SQLException {

            checkColumn(columnIndex);

            Type   sourceType = resultMetaData.columnTypes[columnIndex - 1];
            Object o          = getColumnInType(columnIndex, sourceType);

            if (o == null) {
                return null;
            }

            if (o instanceof Blob) {
                return (Blob) o;
            } else if (o instanceof byte[]) {
                return new JDBCBlob((byte[]) o);
            }

            throw JDBCUtil.sqlException(ErrorCode.X_42561);
        }
