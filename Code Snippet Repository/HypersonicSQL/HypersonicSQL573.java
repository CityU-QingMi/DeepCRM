    public void resolveReferences(Session session, RangeGroup[] rangeGroups) {

        if (isReferencesResolved) {
            return;
        }

        outerRanges = rangeGroups;

        resolveRangeVariables(session, rangeGroups);
        resolveColumnReferencesForAsterisk();
        finaliseColumns();
        resolveColumnReferences(session, rangeGroups);
        setReferenceableColumns();

        unresolvedExpressions = Expression.resolveColumnSet(session,
                RangeVariable.emptyArray, rangeGroups, unresolvedExpressions);
        unionColumnTypes     = new Type[indexLimitVisible];
        isReferencesResolved = true;
    }
