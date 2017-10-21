    void resolveTypesPartOne(Session session) {

        if (isPartOneResolved) {
            return;
        }

        ArrayUtil.projectRowReverse(leftQueryExpression.unionColumnTypes,
                                    leftQueryExpression.unionColumnMap,
                                    unionColumnTypes);
        leftQueryExpression.resolveTypesPartOne(session);
        ArrayUtil.projectRow(leftQueryExpression.unionColumnTypes,
                             leftQueryExpression.unionColumnMap,
                             unionColumnTypes);
        ArrayUtil.projectRowReverse(rightQueryExpression.unionColumnTypes,
                                    rightQueryExpression.unionColumnMap,
                                    unionColumnTypes);
        rightQueryExpression.resolveTypesPartOne(session);
        ArrayUtil.projectRow(rightQueryExpression.unionColumnTypes,
                             rightQueryExpression.unionColumnMap,
                             unionColumnTypes);

        isPartOneResolved = true;
    }
