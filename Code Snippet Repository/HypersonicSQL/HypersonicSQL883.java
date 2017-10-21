    public HashSet getConstraintPath() {

        if (constraintPath == null) {
            constraintPath = new HashSet();
        } else {
            constraintPath.clear();
        }

        return constraintPath;
    }
