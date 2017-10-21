    public String convertToSQLString(Object a) {

        if (a == null) {
            return Tokens.T_NULL;
        }

        Object[]     arra = (Object[]) a;
        StringBuffer sb   = new StringBuffer();

        sb.append(Tokens.T_ARRAY);
        sb.append('[');

        for (int i = 0; i < arra.length; i++) {
            if (i > 0) {
                sb.append(',');
            }

            sb.append(dataType.convertToSQLString(arra[i]));
        }

        sb.append(']');

        return sb.toString();
    }
