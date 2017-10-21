    public String getColumnListSQL(int[] col, int len) {

        StringBuffer sb = new StringBuffer();

        sb.append('(');

        for (int i = 0; i < len; i++) {
            sb.append(getColumn(col[i]).getName().statementName);

            if (i < len - 1) {
                sb.append(',');
            }
        }

        sb.append(')');

        return sb.toString();
    }
