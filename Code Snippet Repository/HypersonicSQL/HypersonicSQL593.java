    public void resetConditions() {

        Index index = joinConditions[0].rangeIndex;

        joinConditions = new RangeVariableConditions[]{
            new RangeVariableConditions(this, true) };
        joinConditions[0].rangeIndex = index;

        //
        index = whereConditions[0].rangeIndex;
        whereConditions = new RangeVariableConditions[]{
            new RangeVariableConditions(this, false) };
        whereConditions[0].rangeIndex = index;
    }
