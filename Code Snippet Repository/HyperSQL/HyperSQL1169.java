    public String getSQL() {

        StringBuffer sb = new StringBuffer();

        sb.append(Tokens.T_ORDER).append(' ').append(Tokens.T_BY).append(' ');

        if (nodes[LEFT].alias != null) {
            sb.append(nodes[LEFT].alias.name);
        } else {
            sb.append(nodes[LEFT].getSQL());
        }

        if (collation != null) {
            sb.append(' ').append(
                collation.getName().getSchemaQualifiedStatementName());
        }

        if (isDescending) {
            sb.append(' ').append(Tokens.T_DESC);
        }

        return sb.toString();
    }
