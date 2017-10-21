    void setupChecks() {

        if (targetTable != baseTable) {
            QuerySpecification select =
                ((TableDerived) targetTable).getQueryExpression()
                    .getMainSelect();

            this.updatableTableCheck = select.checkQueryCondition;
            this.checkRangeVariable =
                select.rangeVariables[select.rangeVariables.length - 1];
        }
    }
