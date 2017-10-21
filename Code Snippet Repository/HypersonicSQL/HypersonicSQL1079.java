    int getConstraintIndex(String constraintName) {

        for (int i = 0, size = constraintList.length; i < size; i++) {
            if (constraintList[i].getName().name.equals(constraintName)) {
                return i;
            }
        }

        return -1;
    }
