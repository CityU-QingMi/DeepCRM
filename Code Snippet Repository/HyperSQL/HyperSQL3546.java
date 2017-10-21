    public boolean hasReference(RangeVariable range) {

        if (unresolvedExpressions == null) {
            return false;
        }

        for (int i = 0; i < unresolvedExpressions.size(); i++) {
            if (((Expression) unresolvedExpressions.get(i)).hasReference(
                    range)) {
                return true;
            }
        }

        return false;
    }
