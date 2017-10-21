    public static String convertToSQLString(Object[] array, Type[] types,
            int maxUnitLength) {

        if (array == null) {
            return Tokens.T_NULL;
        }

        StringBuffer sb = new StringBuffer();

        sb.append('(');

        for (int i = 0; i < array.length; i++) {
            String value;

            if (i > 0) {
                sb.append(',');
            }

            String string = types[i].convertToSQLString(array[i]);

            if (maxUnitLength > 10 && string.length() > maxUnitLength) {
                sb.append(string.substring(0, maxUnitLength - 4));
                sb.append(" ...");
            } else {
                sb.append(string);
            }
        }

        sb.append(')');

        return sb.toString();
    }
