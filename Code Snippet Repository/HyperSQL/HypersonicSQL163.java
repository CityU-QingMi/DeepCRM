    void getJoinRangeVariables(RangeVariable[] ranges, HsqlList list) {

        if (opType == OpTypes.COLUMN) {
            for (int i = 0; i < ranges.length; i++) {
                if (ranges[i] == rangeVariable) {
                    list.add(rangeVariable);

                    return;
                }
            }
        }
    }
