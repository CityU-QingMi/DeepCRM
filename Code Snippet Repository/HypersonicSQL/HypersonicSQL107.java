    public void setCorrelatedReferences(RangeGroup resolvedRangeGroup) {

        if (rangeGroups == null) {
            for (int i = 0; i < nodes.length; i++) {
                if (nodes[i] != null) {
                    nodes[i].setCorrelatedReferences(resolvedRangeGroup);
                }
            }
        } else if (ArrayUtil.find(rangeGroups, resolvedRangeGroup) > -1) {
            for (int idx = rangeGroups.length - 1; idx >= 0; idx--) {
                if (rangeGroups[idx] == resolvedRangeGroup) {
                    break;
                }

                rangeGroups[idx].setCorrelated();
            }

            rangeGroup.setCorrelated();
        }
    }
