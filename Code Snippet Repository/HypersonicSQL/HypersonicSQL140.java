    public HsqlList resolveColumnReferences(Session session,
            RangeGroup rangeGroup, int rangeCount, RangeGroup[] rangeGroups,
            HsqlList unresolvedSet, boolean acceptsSequences) {

        HsqlList conditionSet = nodes[RIGHT].resolveColumnReferences(session,
            rangeGroup, rangeCount, rangeGroups, null, false);

        if (conditionSet != null) {
            ExpressionColumn.checkColumnsResolved(conditionSet);
        }

        if (unresolvedSet == null) {
            unresolvedSet = new ArrayListIdentity();
        }

        unresolvedSet.add(this);

        if (rangeGroup.getRangeVariables().length > 0) {
            this.rangeGroups = rangeGroups;
            this.rangeGroup  = rangeGroup;
        }

        return unresolvedSet;
    }
