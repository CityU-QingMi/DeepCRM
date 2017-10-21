    private void getFKStatement(StringBuffer sb) {

        if (!getName().isReservedName()) {
            sb.append(Tokens.T_CONSTRAINT).append(' ');
            sb.append(getName().statementName);
            sb.append(' ');
        }

        sb.append(Tokens.T_FOREIGN).append(' ').append(Tokens.T_KEY);

        int[] col = getRefColumns();

        sb.append(getRef().getColumnListSQL(col, col.length));
        sb.append(' ').append(Tokens.T_REFERENCES).append(' ');
        sb.append(getMain().getName().getSchemaQualifiedStatementName());

        col = getMainColumns();

        sb.append(getMain().getColumnListSQL(col, col.length));

        if (getDeleteAction() != SchemaObject.ReferentialAction.NO_ACTION) {
            sb.append(' ').append(Tokens.T_ON).append(' ').append(
                Tokens.T_DELETE).append(' ');
            sb.append(getDeleteActionString());
        }

        if (getUpdateAction() != SchemaObject.ReferentialAction.NO_ACTION) {
            sb.append(' ').append(Tokens.T_ON).append(' ').append(
                Tokens.T_UPDATE).append(' ');
            sb.append(getUpdateActionString());
        }
    }
