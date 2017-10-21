    private void readIndex(Table table, HsqlArrayList indexList) {

        HsqlName indexHsqlName;

        read();

        indexHsqlName = readNewSchemaObjectName(SchemaObject.INDEX, true);

        HsqlName tableSchema = table.getSchemaName();

        indexHsqlName.schema = tableSchema;
        indexHsqlName.parent = table.getName();
        indexHsqlName.schema = table.getSchemaName();

        if (readIfThis(Tokens.USING)) {
            if ("BTREE".equals(token.tokenString)
                    || "HASH".equals(token.tokenString)) {
                read();
            }
        }

        readThis(Tokens.ON);

        int[] indexColumns = readColumnList(table, true);
        Constraint c = new Constraint(indexHsqlName, table, indexColumns,
                                      SchemaObject.INDEX);

        indexList.add(c);
    }
