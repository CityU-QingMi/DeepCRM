    public String toString() {

        StringBuffer sb = new StringBuffer();

        sb.append(super.toString());

        if (columnCount == 0) {
            sb.append("[columnCount=0]");

            return sb.toString();
        }
        sb.append('[');

        for (int i = 0; i < columnCount; i++) {
            JDBCColumnMetaData meta = getColumnMetaData(i + 1);

            sb.append('\n');
            sb.append("   column_");
            sb.append(i + 1);
            sb.append('=');
            sb.append(meta);

            if (i + 1 < columnCount) {
                sb.append(',');
                sb.append(' ');
            }
        }
        sb.append('\n');
        sb.append(']');

        return sb.toString();
    }
