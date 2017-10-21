    private void setParameters() {

        namedJoinColumnExpressions = new HashMap();

        QuerySpecification qs =
            (QuerySpecification) this.rangeTable.getQueryExpression();

        this.rangeArray = qs.rangeVariables;

        for (int i = 0; i < rangeArray.length; i++) {
            if (rangeArray[i].isLeftJoin) {
                hasLeftJoin = true;
            }

            if (rangeArray[i].isRightJoin) {
                hasRightJoin = true;
            }

            if (rangeArray[i].isLateral) {
                hasLateral = true;
            }

            if (rangeArray[i].namedJoinColumnExpressions != null) {
                namedJoinColumnExpressions.putAll(
                    rangeArray[i].namedJoinColumnExpressions);
            }
        }
    }
