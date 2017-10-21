    public String getSQLForClustered() {

        if (!isCached() && !isText()) {
            return null;
        }

        Index index = getClusteredIndex();

        if (index == null) {
            return null;
        }

        String colList = getColumnListSQL(index.getColumns(),
                                          index.getColumnCount());
        StringBuffer sb = new StringBuffer(64);

        sb.append(Tokens.T_SET).append(' ').append(Tokens.T_TABLE).append(' ');
        sb.append(getName().getSchemaQualifiedStatementName());
        sb.append(' ').append(Tokens.T_CLUSTERED).append(' ');
        sb.append(Tokens.T_ON).append(' ').append(colList);

        return sb.toString();
    }
