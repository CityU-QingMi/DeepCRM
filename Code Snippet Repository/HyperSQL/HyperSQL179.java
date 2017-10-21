    String getDataSourceHeader() {

        String header = getHeader();

        if (header == null) {
            return null;
        }

        StringBuffer sb = new StringBuffer(128);

        sb.append(Tokens.T_SET).append(' ').append(Tokens.T_TABLE).append(' ');
        sb.append(getName().getSchemaQualifiedStatementName());
        sb.append(' ').append(Tokens.T_SOURCE).append(' ');
        sb.append(Tokens.T_HEADER).append(' ');
        sb.append(header);

        return sb.toString();
    }
