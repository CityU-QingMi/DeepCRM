    private void checkUpdatable(int columnIndex) throws SQLException {

        checkClosed();
        checkColumn(columnIndex);

        if (!isUpdatable) {
            throw JDBCUtil.notUpdatableColumn();
        }

        if (resultMetaData.colIndexes[--columnIndex] == -1) {
            throw JDBCUtil.notUpdatableColumn();
        }

        if (!resultMetaData.columns[columnIndex].isWriteable()) {
            throw JDBCUtil.notUpdatableColumn();
        }
    }
