    public boolean canAccesssNonSelect() {

        if (isFull) {
            return true;
        }

        if (isFullInsert || isFullUpdate || isFullDelete || isFullReferences
                || isFullTrigger) {
            return true;
        }

        boolean result = false;

        result |= (insertColumnSet != null && !insertColumnSet.isEmpty());
        result |= (updateColumnSet != null && !updateColumnSet.isEmpty());
        result |= referencesColumnSet != null
                  && !referencesColumnSet.isEmpty();
        result |= triggerColumnSet != null && !triggerColumnSet.isEmpty();

        return result;
    }
