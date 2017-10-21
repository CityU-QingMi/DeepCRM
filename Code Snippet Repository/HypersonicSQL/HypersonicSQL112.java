    int getDegree() {

        switch (opType) {

            case OpTypes.ROW :
                return nodes.length;

            case OpTypes.TABLE :
            case OpTypes.ROW_SUBQUERY :
            case OpTypes.TABLE_SUBQUERY :
                if (table == null) {
                    return nodeDataTypes.length;
                }

                return table.queryExpression.getColumnCount();

            default :
                return 1;
        }
    }
