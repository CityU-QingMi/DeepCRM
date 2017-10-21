    public void addConditionState(String sqlState) {

        boolean result = conditionStates.add(sqlState);

        result &= conditionGroups.isEmpty();

        if (!result) {
            throw Error.error(ErrorCode.X_42612);
        }
    }
