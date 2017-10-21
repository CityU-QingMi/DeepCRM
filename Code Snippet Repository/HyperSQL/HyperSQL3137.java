    public void addConstraint(Constraint c) {

        int position = constraints.length;

        constraints = (Constraint[]) ArrayUtil.resizeArray(constraints,
                position + 1);
        constraints[position] = c;

        setNotNull();
    }
