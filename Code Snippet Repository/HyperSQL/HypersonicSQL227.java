    public HsqlList resolveColumnReferences(Session session,
            RangeGroup rangeGroup, int rangeCount, RangeGroup[] rangeGroups,
            HsqlList unresolvedSet, boolean acceptsSequences) {

        HsqlList conditionSet = condition.resolveColumnReferences(session,
            rangeGroup, rangeCount, rangeGroups, null, false);

        if (conditionSet != null) {
            ExpressionColumn.checkColumnsResolved(conditionSet);
        }

        if (isSelfAggregate()) {
            if (unresolvedSet == null) {
                unresolvedSet = new ArrayListIdentity();
            }

            unresolvedSet.add(this);

            return unresolvedSet;
        } else {
            return super.resolveColumnReferences(session, rangeGroup,
                                                 rangeCount, rangeGroups,
                                                 unresolvedSet,
                                                 acceptsSequences);
        }
    }
