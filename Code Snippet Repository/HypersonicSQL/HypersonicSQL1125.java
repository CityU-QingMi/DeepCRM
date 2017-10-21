    public boolean isRecompiled() {

        if (canRecompile && queryExpression instanceof QuerySpecification) {
            QuerySpecification qs = (QuerySpecification) queryExpression;

            if (qs.isAggregated || qs.isGrouped || qs.isOrderSensitive) {
                return false;
            } else {
                return true;
            }
        }

        return false;
    }
