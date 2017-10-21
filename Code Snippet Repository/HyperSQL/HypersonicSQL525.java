    public boolean hasReference(RangeVariable range) {

        if (leftQueryExpression.hasReference(range)) {
            return true;
        }

        if (rightQueryExpression.hasReference(range)) {
            return true;
        }

        return false;
    }
