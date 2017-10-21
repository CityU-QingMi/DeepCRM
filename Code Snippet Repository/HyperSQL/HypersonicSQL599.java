    void resolveRangeTableTypes(Session session, RangeVariable[] ranges) {

        QueryExpression queryExpression = rangeTable.getQueryExpression();

        if (queryExpression != null) {
            if (queryExpression instanceof QuerySpecification) {
                QuerySpecification qs = (QuerySpecification) queryExpression;

                if (qs.isGrouped || qs.isAggregated || qs.isOrderSensitive) {

                    //
                } else {
                    moveConditionsToInner(session, ranges);
                }
            }

            queryExpression.resolveTypesPartThree(session);
        }
    }
