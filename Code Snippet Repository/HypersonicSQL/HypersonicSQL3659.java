    public String convertToSQLString(Object a) {

        if (a == null) {
            return Tokens.T_NULL;
        }

        StringBuffer sb = new StringBuffer(32);

        switch (typeCode) {

            case Types.SQL_DATE :
                sb.append(Tokens.T_DATE);
                break;

            case Types.SQL_TIME_WITH_TIME_ZONE :
            case Types.SQL_TIME :
                sb.append(Tokens.T_TIME);
                break;

            case Types.SQL_TIMESTAMP_WITH_TIME_ZONE :
            case Types.SQL_TIMESTAMP :
                sb.append(Tokens.T_TIMESTAMP);
                break;
        }

        sb.append(StringConverter.toQuotedString(convertToString(a), '\'',
                false));

        return sb.toString();
    }
