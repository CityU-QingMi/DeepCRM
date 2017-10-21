    public String getSQLForReadOnly() {

        if (isReadOnly) {
            StringBuffer sb = new StringBuffer(64);

            sb.append(Tokens.T_SET).append(' ').append(Tokens.T_TABLE).append(
                ' ');
            sb.append(getName().getSchemaQualifiedStatementName());
            sb.append(' ').append(Tokens.T_READ).append(' ');
            sb.append(Tokens.T_ONLY);

            return sb.toString();
        } else {
            return null;
        }
    }
