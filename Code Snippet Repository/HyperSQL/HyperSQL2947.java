    public String getSQL() {

        StringBuffer sb = new StringBuffer();

        sb.append(Tokens.T_CREATE).append(' ');
        sb.append(Tokens.T_COLLATION).append(' ');

        if (SqlInvariants.INFORMATION_SCHEMA.equals(name.schema.name)) {
            sb.append(name.getStatementName());
        } else {
            sb.append(name.getSchemaQualifiedStatementName());
        }

        sb.append(' ').append(Tokens.T_FOR).append(' ');

        if (SqlInvariants.INFORMATION_SCHEMA.equals(
                charset.name.schema.name)) {
            sb.append(charset.name.getStatementName());
        } else {
            sb.append(charset.name.getSchemaQualifiedStatementName());
        }

        sb.append(' ').append(Tokens.T_FROM).append(' ');
        sb.append(sourceName.statementName);
        sb.append(' ');

        if (!padSpace) {
            sb.append(Tokens.T_NO).append(' ').append(Tokens.T_PAD);
        }

        return sb.toString();
    }
