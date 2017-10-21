    public String convertToSQLString(Object a) {

        if (a == null) {
            return Tokens.T_NULL;
        }

        String s = convertToString(a);

        return StringConverter.toQuotedString(s, '\'', true);
    }
