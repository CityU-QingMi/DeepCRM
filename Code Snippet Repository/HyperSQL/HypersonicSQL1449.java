    private void startUpdate() throws SQLException {

        if (originalBlob != null) {
            return;
        }

        originalBlob = blob;
        blob         = (BlobDataID) blob.duplicate(session);

        resultSet.startUpdate(colIndex + 1);

        resultSet.preparedStatement.parameterValues[colIndex] = blob;
        resultSet.preparedStatement.parameterSet[colIndex]    = Boolean.TRUE;
    }
