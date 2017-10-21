        public Clob getClob(int columnIndex) throws SQLException {

            checkColumn(columnIndex);

            Type   sourceType = resultMetaData.columnTypes[columnIndex - 1];
            Object o          = getColumnInType(columnIndex, sourceType);

            if (o == null) {
                return null;
            }

            if (o instanceof Clob) {
                return (Clob) o;
            } else if (o instanceof String) {
                return new JDBCClob((String) o);
            }

            throw JDBCUtil.sqlException(ErrorCode.X_42561);
        }
