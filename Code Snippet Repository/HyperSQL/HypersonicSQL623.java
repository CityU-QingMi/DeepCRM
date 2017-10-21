    public boolean hasAnyIndexCondition() {

        for (int i = 0; i < joinConditions.length; i++) {
            if (joinConditions[0].indexedColumnCount > 0) {
                return true;
            }
        }

        for (int i = 0; i < whereConditions.length; i++) {
            if (whereConditions[0].indexedColumnCount > 0) {
                return true;
            }
        }

        return false;
    }
