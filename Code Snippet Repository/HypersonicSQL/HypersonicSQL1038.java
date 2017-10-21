    int getNextConstraintIndex(int from, int type) {

        for (int i = from, size = constraintList.length; i < size; i++) {
            Constraint c = constraintList[i];

            if (c.getConstraintType() == type) {
                return i;
            }
        }

        return -1;
    }
