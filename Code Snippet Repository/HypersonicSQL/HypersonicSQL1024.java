    public String getColumnListWithTypeSQL() {

        StringBuffer sb = new StringBuffer();

        sb.append('(');

        for (int j = 0; j < columnCount; j++) {
            ColumnSchema column  = getColumn(j);
            String       colname = column.getName().statementName;
            Type         type    = column.getDataType();

            if (j > 0) {
                sb.append(',');
            }

            sb.append(colname);
            sb.append(' ');
            sb.append(type.getTypeDefinition());
        }

        sb.append(')');

        return sb.toString();
    }
