    void setMergeability() {

        isOrderSensitive |= sortAndSlice.hasLimit() || sortAndSlice.hasOrder();

        if (isOrderSensitive) {
            isMergeable = false;
        }

        if (isAggregated) {
            isMergeable = false;
        }

        if (isGrouped || isDistinctSelect) {
            isMergeable = false;
        }

        if (rangeVariables.length != 1) {
            isBaseMergeable = false;
            isMergeable     = false;
        }
    }
