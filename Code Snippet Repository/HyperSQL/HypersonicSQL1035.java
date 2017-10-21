    Constraint getUniqueConstraintForColumns(int[] cols) {

        for (int i = 0, size = constraintList.length; i < size; i++) {
            Constraint c = constraintList[i];

            if (c.isUniqueWithColumns(cols)) {
                return c;
            }
        }

        return null;
    }
