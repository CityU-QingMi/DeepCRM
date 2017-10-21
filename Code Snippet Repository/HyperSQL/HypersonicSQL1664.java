    public java.io.Reader getCharacterStream(
            int columnIndex) throws SQLException {

        checkColumn(columnIndex);

        Type   sourceType = resultMetaData.columnTypes[columnIndex - 1];
        Object o          = getColumnInType(columnIndex, sourceType);

        if (o == null) {
            return null;
        }

        if (o instanceof ClobDataID) {
            return ((ClobDataID) o).getCharacterStream(session);
        } else if (o instanceof Clob) {
            return ((Clob) o).getCharacterStream();
        } else if (o instanceof String) {
            return new StringReader((String) o);
        }

        throw JDBCUtil.sqlException(ErrorCode.X_42561);
    }
