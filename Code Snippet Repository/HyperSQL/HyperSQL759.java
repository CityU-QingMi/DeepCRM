    public Clob getClob(int columnIndex) throws SQLException {

        checkColumn(columnIndex);

        Type   sourceType = resultMetaData.columnTypes[columnIndex - 1];
        Object o          = getColumnInType(columnIndex, sourceType);

        if (o == null) {
            return null;
        }

        if (o instanceof ClobDataID) {
            JDBCClobClient clob = new JDBCClobClient(session, (ClobDataID) o);

            if (isUpdatable) {
                if (resultMetaData.colIndexes[columnIndex - 1] > 0
                        && resultMetaData.columns[columnIndex - 1]
                            .isWriteable()) {
                    clob.setWritable(this, columnIndex - 1);
                }
            }

            return clob;
        } else if (o instanceof Clob) {
            return (Clob) o;
        } else if (o instanceof String) {
            return new JDBCClob((String) o);
        }

        throw JDBCUtil.sqlException(ErrorCode.X_42561);
    }
