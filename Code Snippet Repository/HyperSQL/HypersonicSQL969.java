    public boolean handlesCondition(String sqlState) {

        if (conditionStates.contains(sqlState)) {
            return true;
        }

        String conditionClass = sqlState.substring(0, 2);

        if (conditionStates.contains(conditionClass)) {
            return true;
        }

        if (conditionClass.equals("01")) {
            return conditionGroups.contains(SQL_WARNING);
        }

        if (conditionClass.equals("02")) {
            return conditionGroups.contains(SQL_NOT_FOUND);
        }

        return conditionGroups.contains(SQL_EXCEPTION);
    }
