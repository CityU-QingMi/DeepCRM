    void resolveTypesPartOne(Session session) {

        if (isPartOneResolved) {
            return;
        }

        resolveExpressionTypes(session);
        resolveAggregates();

        for (int i = 0; i < unionColumnTypes.length; i++) {
            unionColumnTypes[i] = Type.getAggregateType(unionColumnTypes[i],
                    exprColumns[i].getDataType());
        }

        isPartOneResolved = true;
    }
