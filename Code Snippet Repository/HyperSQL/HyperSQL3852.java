    public String[] getSettingsSQL() {

        HsqlArrayList list = new HsqlArrayList();
        StringBuffer  sb   = new StringBuffer();

        if (!getCatalogName().name.equals(
                SqlInvariants.DEFAULT_CATALOG_NAME)) {
            String name = getCatalogName().statementName;

            sb.append("ALTER CATALOG PUBLIC RENAME TO ").append(name);
            list.add(sb.toString());
            sb.setLength(0);
        }

        if (!collation.isDefaultCollation()) {
            list.add(collation.getDatabaseCollationSQL());
        }

        HashMappedList lobTables =
            schemaManager.getTables(SqlInvariants.LOBS_SCHEMA);

        for (int i = 0; i < lobTables.size(); i++) {
            Table table = (Table) lobTables.get(i);

            if (table.isCached()) {
                sb.append(Tokens.T_SET).append(' ').append(Tokens.T_TABLE);
                sb.append(' ');
                sb.append(table.getName().getSchemaQualifiedStatementName());
                sb.append(' ').append(Tokens.T_TYPE).append(' ');
                sb.append(Tokens.T_CACHED);
                list.add(sb.toString());
                sb.setLength(0);
            }
        }

        String[] array = new String[list.size()];

        list.toArray(array);

        return array;
    }
