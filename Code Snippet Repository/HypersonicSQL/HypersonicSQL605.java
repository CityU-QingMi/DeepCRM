        private RangeIteratorMain(Session session, RangeVariable rangeVar) {

            this.rangePosition = rangeVar.rangePosition;
            this.store         = rangeVar.rangeTable.getRowStore(session);
            this.session       = session;
            this.rangeVar      = rangeVar;
            isBeforeFirst      = true;
            whereConditions    = rangeVar.whereConditions;
            joinConditions     = rangeVar.joinConditions;

            if (rangeVar.isRightJoin) {
                lookup = new OrderedLongHashSet();
            }

            conditions = rangeVar.joinConditions;

            if (rangeVar.whereConditions[0].hasIndexCondition()) {
                conditions = rangeVar.whereConditions;
            }
        }
