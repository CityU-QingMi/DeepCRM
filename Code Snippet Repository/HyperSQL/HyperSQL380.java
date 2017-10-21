    public HsqlList resolveColumnReferences(Session session,
            RangeGroup rangeGroup, int rangeCount,
            RangeGroup[] rangeGroups, HsqlList unresolvedSet, boolean acceptsSequences) {

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == null) {
                continue;
            }

            unresolvedSet = nodes[i].resolveColumnReferences(session,
                    rangeGroup, rangeCount, rangeGroups, unresolvedSet, acceptsSequences);
        }

        return unresolvedSet;
    }
