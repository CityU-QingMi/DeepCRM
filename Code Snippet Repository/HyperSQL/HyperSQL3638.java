    public String getSQLBodyDefinition() {

        StringBuffer sb = new StringBuffer();

        if (language == LANGUAGE_JAVA) {
            sb.append(Tokens.T_EXTERNAL).append(' ').append(Tokens.T_NAME);
            sb.append(' ').append('\'').append(methodName).append('\'');
        } else {
            sb.append(statement.getSQL());
        }

        return sb.toString();
    }
