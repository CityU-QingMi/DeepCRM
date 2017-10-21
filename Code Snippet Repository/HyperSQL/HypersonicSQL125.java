    static HsqlList resolveColumnSet(Session session,
                                     RangeVariable[] rangeVars,
                                     int rangeCount, RangeGroup[] rangeGroups,
                                     HsqlList sourceSet, HsqlList targetSet) {

        if (sourceSet == null) {
            return targetSet;
        }

        RangeGroup rangeGroup = new RangeGroupSimple(rangeVars, false);

        for (int i = 0; i < sourceSet.size(); i++) {
            Expression e = (Expression) sourceSet.get(i);

            targetSet = e.resolveColumnReferences(session, rangeGroup,
                                                  rangeCount, rangeGroups,
                                                  targetSet, false);
        }

        return targetSet;
    }
