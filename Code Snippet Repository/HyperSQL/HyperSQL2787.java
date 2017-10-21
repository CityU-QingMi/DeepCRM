    private void createView(String viewName, String[] columnList,
                            String viewStatement) throws SQLException {

        StringBuffer colList = new StringBuffer();

        if (columnList != null) {
            colList.append(" (");

            for (int i = 0; i < columnList.length; ++i) {
                colList.append('"').append(columnList[i]).append('"');

                if (i < columnList.length - 1) {
                    colList.append(',');
                }
            }

            colList.append(")");
        }

        executeStatement("CREATE VIEW " + viewName + colList.toString()
                         + " AS " + viewStatement);

        if (columnList != null) {
            ensureTableColumns(viewName, columnList);
        }
    }
