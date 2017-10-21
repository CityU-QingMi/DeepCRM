    public String getSQLForTableSpace() {

        if (!isCached() || tableSpace == DataSpaceManager.tableIdDefault) {
            return null;
        }

        StringBuffer sb = new StringBuffer(64);

        sb.append(Tokens.T_SET).append(' ').append(Tokens.T_TABLE).append(' ');
        sb.append(getName().getSchemaQualifiedStatementName());
        sb.append(' ').append(Tokens.T_SPACE).append(' ').append(tableSpace);

        return sb.toString();
    }
