    public String getSQL() {

        StringBuffer sb = new StringBuffer();

        sb.append(Tokens.T_CREATE).append(' ').append(
            Tokens.T_CHARACTER).append(' ').append(Tokens.T_SET).append(' ');

        if (SqlInvariants.INFORMATION_SCHEMA.equals(name.schema.name)) {
            sb.append(name.getStatementName());
        } else {
            sb.append(name.getSchemaQualifiedStatementName());
        }

        if (base != null) {
            sb.append(' ').append(Tokens.T_AS).append(' ').append(Tokens.T_GET);
            sb.append(' ');

            if (SqlInvariants.INFORMATION_SCHEMA.equals(base.schema.name)) {
                sb.append(base.getStatementName());
            } else {
                sb.append(base.getSchemaQualifiedStatementName());
            }
        }

        return sb.toString();
    }
