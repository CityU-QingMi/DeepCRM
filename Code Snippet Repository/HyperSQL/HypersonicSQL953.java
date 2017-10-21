    private StringBuffer appendColumns(StringBuffer sb, int[] columnMap) {

        if (columnMap == null || updateExpressions.length == 0) {
            return sb;
        }

        sb.append("COLUMNS=[");

        for (int i = 0; i < columnMap.length; i++) {
            sb.append('\n').append(columnMap[i]).append(':').append(
                ' ').append(
                targetTable.getColumn(columnMap[i]).getNameString());
        }

        for (int i = 0; i < updateExpressions.length; i++) {
            sb.append('[').append(updateExpressions[i]).append(']');
        }

        sb.append(']');

        return sb;
    }
