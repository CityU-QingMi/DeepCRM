    void startUpdate(int columnIndex) throws SQLException {

        checkUpdatable(columnIndex);

        if (currentUpdateRowNumber != navigator.getRowNumber()) {
            preparedStatement.clearParameters();
        }
        currentUpdateRowNumber = navigator.getRowNumber();
        isRowUpdated           = true;
    }
