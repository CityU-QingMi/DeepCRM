    boolean isConditionRangeVariable(RangeVariable range) {

        if (nodes[LEFT].getRangeVariable() == range) {
            return true;
        }

        if (nodes[RIGHT].getRangeVariable() == range) {
            return true;
        }

        return false;
    }
