    public void addConditionType(int conditionType) {

        boolean result = conditionGroups.add(conditionType);

        result &= conditionStates.isEmpty();

        if (!result) {
            throw Error.error(ErrorCode.X_42612);
        }
    }
