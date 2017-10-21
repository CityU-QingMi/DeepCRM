    public String getIndexRootsSQL(long[] roots) {

        StringBuffer sb = new StringBuffer(128);

        sb.append(Tokens.T_SET).append(' ').append(Tokens.T_TABLE).append(' ');
        sb.append(getName().getSchemaQualifiedStatementName());
        sb.append(' ').append(Tokens.T_INDEX).append(' ').append('\'');
        sb.append(StringUtil.getList(roots, " ", ""));
        sb.append(' ');
        sb.append(StringUtil.getList(new long[indexList.length], " ", ""));
        sb.append(' ').append(store.elementCount());
        sb.append('\'');

        return sb.toString();
    }
