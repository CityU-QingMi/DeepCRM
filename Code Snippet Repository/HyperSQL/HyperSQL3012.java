    public String convertToSQLString(Object a) {

        if (a == null) {
            return Tokens.T_NULL;
        }

        StringBuffer sb = new StringBuffer(32);

        sb.append(Tokens.T_INTERVAL).append(' ');
        sb.append('\'').append(convertToString(a)).append('\'').append(' ');
        sb.append(Tokens.SQL_INTERVAL_FIELD_NAMES[startPartIndex]);

        if (startPartIndex != endPartIndex) {
            sb.append(' ');
            sb.append(Tokens.T_TO);
            sb.append(' ');
            sb.append(Tokens.SQL_INTERVAL_FIELD_NAMES[endPartIndex]);
        }

        return sb.toString();
    }
