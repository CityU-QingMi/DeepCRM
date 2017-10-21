    public HsqlName[] getResultColumnNames() {

        if (resultTable == null) {
            return leftQueryExpression.getResultColumnNames();
        }

        HashMappedList list = ((TableDerived) resultTable).columnList;
        HsqlName[]     resultColumnNames = new HsqlName[list.size()];

        for (int i = 0; i < resultColumnNames.length; i++) {
            resultColumnNames[i] = ((ColumnSchema) list.get(i)).getName();
        }

        return resultColumnNames;
    }
