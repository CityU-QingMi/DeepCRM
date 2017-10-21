    private boolean resolveCorrelated(RangeGroup rangeGroup,
                                      RangeGroup[] rangeGroups) {

        for (int idx = rangeGroups.length - 1; idx >= 0; idx--) {
            RangeVariable[] rangeVarArray =
                rangeGroups[idx].getRangeVariables();

            for (int i = 0; i < rangeVarArray.length; i++) {
                RangeVariable rangeVar = rangeVarArray[i];

                if (rangeVar == null) {
                    continue;
                }

                if (resolveColumnReference(rangeVar, true)) {
                    if (opType == OpTypes.COLUMN) {
                        rangeGroup.setCorrelated();

                        for (int idxx = rangeGroups.length - 1; idxx > idx;
                                idxx--) {
                            rangeGroups[idxx].setCorrelated();
                        }
                    }

                    return true;
                }
            }
        }

        return false;
    }
