    public String[] getAuthenticationSQL() {

        HsqlArrayList list = new HsqlArrayList();
        String[]      array;

        if (pwCheckFunction != null) {
            StringBuffer sb = new StringBuffer();

            sb.append(Tokens.T_SET).append(' ').append(Tokens.T_DATABASE);
            sb.append(' ').append(Tokens.T_PASSWORD).append(' ');
            sb.append(Tokens.T_CHECK).append(' ').append(Tokens.T_FUNCTION);
            sb.append(' ');
            sb.append(pwCheckFunction.getSQLBodyDefinition());
            list.add(sb.toString());
        }

        if (extAuthenticationFunction != null) {
            StringBuffer sb = new StringBuffer();

            sb.append(Tokens.T_SET).append(' ').append(Tokens.T_DATABASE);
            sb.append(' ').append(Tokens.T_AUTHENTICATION).append(' ');
            sb.append(Tokens.T_FUNCTION).append(' ');
            sb.append(extAuthenticationFunction.getSQLBodyDefinition());
            list.add(sb.toString());
        }

        array = new String[list.size()];

        list.toArray(array);

        return array;
    }
