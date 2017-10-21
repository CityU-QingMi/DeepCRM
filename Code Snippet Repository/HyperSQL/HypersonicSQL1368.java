    boolean hasNulls(Session session, Object[] rowData) {

        boolean uniqueNulls = session == null
                              || session.database.sqlUniqueNulls;
        boolean compareId = false;

        for (int j = 0; j < colIndex.length; j++) {
            if (rowData[colIndex[j]] == null) {
                compareId = true;

                if (uniqueNulls) {
                    break;
                }
            } else if (!uniqueNulls) {
                compareId = false;

                break;
            }
        }

        return compareId;
    }
