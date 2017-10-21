    public static String getRestartSQL(Table t) {

        String colname = t.getColumn(t.identityColumn).getName().statementName;
        NumberSequence seq = t.identitySequence;
        StringBuffer   sb  = new StringBuffer(128);

        sb.append(Tokens.T_ALTER).append(' ').append(Tokens.T_TABLE);
        sb.append(' ').append(t.getName().getSchemaQualifiedStatementName());
        sb.append(' ').append(Tokens.T_ALTER).append(' ');
        sb.append(Tokens.T_COLUMN);
        sb.append(' ').append(colname);
        sb.append(' ').append(Tokens.T_RESTART);
        sb.append(' ').append(Tokens.T_WITH).append(' ').append(seq.peek());

        return sb.toString();
    }
