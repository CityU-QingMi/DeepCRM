    void prepareDomainCheckConstraint(Session session) {

        // to ensure no subselects etc. are in condition
        check.checkValidCheckConstraint();

        HsqlList list = check.resolveColumnReferences(session,
            RangeGroup.emptyGroup, 0, RangeGroup.emptyArray, null, false);

        if (list != null) {
            Expression e = ((Expression) list.get(0));

            throw Error.error(ErrorCode.X_42501, e.getSQL());
        }

        check.resolveTypes(session, null);
    }
