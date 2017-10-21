    public String convertToSQLString(Object a) {

        if (a == null) {
            return Tokens.T_NULL;
        }

        Object[]     array = (Object[]) a;
        StringBuffer sb    = new StringBuffer();

        sb.append(Tokens.T_ROW);
        sb.append('(');

        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                sb.append(',');
            }

            String string = dataTypes[i].convertToSQLString(array[i]);

            sb.append(string);
        }

        sb.append(')');

        return sb.toString();
    }
