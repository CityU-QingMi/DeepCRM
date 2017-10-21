    public void resolve(Session session, RangeGroup[] rangeGroups,
                        Type[] targetTypes) {

        resolveReferences(session, rangeGroups);
        ExpressionColumn.checkColumnsResolved(unresolvedExpressions);
        resolveTypesPartOne(session);

        if (targetTypes != null) {
            for (int i = 0;
                    i < unionColumnTypes.length && i < targetTypes.length;
                    i++) {
                if (unionColumnTypes[i] == null) {
                    unionColumnTypes[i] = targetTypes[i];
                }
            }
        }

        resolveTypesPartTwo(session);
        resolveTypesPartThree(session);
    }
