    void setColDefaultExpression(int colIndex, Expression def) {

        if (def == null) {
            table.checkColumnInFKConstraint(
                colIndex, SchemaObject.ReferentialAction.SET_DEFAULT);
        }

        ColumnSchema column = table.getColumn(colIndex);

        column.setDefaultExpression(def);
        table.setColumnTypeVars(colIndex);
    }
