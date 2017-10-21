    Constraint getNotNullConstraintForColumn(int colIndex) {

        for (int i = 0, size = constraintList.length; i < size; i++) {
            Constraint c = constraintList[i];

            if (c.isNotNull() && c.notNullColumnIndex == colIndex) {
                return c;
            }
        }

        return null;
    }
