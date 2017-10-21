    public void removeConstraint(String name) {

        for (int i = 0; i < constraints.length; i++) {
            if (constraints[i].getName().name.equals(name)) {
                constraints =
                    (Constraint[]) ArrayUtil.toAdjustedArray(constraints,
                        null, i, -1);

                break;
            }
        }

        setNotNull();
    }
