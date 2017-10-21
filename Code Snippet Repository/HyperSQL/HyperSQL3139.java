    public Constraint getConstraint(String name) {

        for (int i = 0; i < constraints.length; i++) {
            if (constraints[i].getName().name.equals(name)) {
                return constraints[i];
            }
        }

        return null;
    }
