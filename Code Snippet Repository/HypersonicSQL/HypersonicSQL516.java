    public void setTableColumnNames(HashMappedList list) {

        if (resultTable != null) {
            ((TableDerived) resultTable).columnList = list;

            return;
        }

        leftQueryExpression.setTableColumnNames(list);
    }
