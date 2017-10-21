    private void resolveRangeVariables(Session session,
                                       RangeGroup[] rangeGroups) {

        if (rangeVariables == null
                || rangeVariables.length < rangeVariableList.size()) {
            rangeVariables = new RangeVariable[rangeVariableList.size()];

            rangeVariableList.toArray(rangeVariables);
        }

        for (int i = 0; i < rangeVariables.length; i++) {
            RangeGroup rangeGroup;

            if (rangeVariables[i].isLateral) {
                RangeVariable[] rangeVars =
                    (RangeVariable[]) ArrayUtil.resizeArray(rangeVariables, i);

                rangeGroup = new RangeGroupSimple(rangeVars, this);
            } else if (rangeGroups == RangeGroup.emptyArray) {
                rangeGroup = RangeGroup.emptyGroup;
            } else {
                rangeGroup = new RangeGroupSimple(RangeVariable.emptyArray,
                                                  this);
            }

            rangeVariables[i].resolveRangeTable(session, rangeGroup,
                                                rangeGroups);
        }
    }
