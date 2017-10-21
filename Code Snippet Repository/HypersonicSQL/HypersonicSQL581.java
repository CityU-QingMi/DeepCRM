    public boolean reverseOrder() {

        if (joinConditions.length == 1) {
            joinConditions[0].reverseIndexCondition();

            return true;
        }

        return false;
    }
