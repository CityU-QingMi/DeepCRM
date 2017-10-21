    public boolean isEmpty() {

        if (isFull || isFullSelect || isFullInsert || isFullUpdate
                || isFullReferences || isFullDelete) {
            return false;
        }

        if (selectColumnSet != null && !selectColumnSet.isEmpty()) {
            return false;
        }

        if (insertColumnSet != null && !insertColumnSet.isEmpty()) {
            return false;
        }

        if (updateColumnSet != null && !updateColumnSet.isEmpty()) {
            return false;
        }

        if (referencesColumnSet != null && !referencesColumnSet.isEmpty()) {
            return false;
        }

        if (triggerColumnSet != null && !triggerColumnSet.isEmpty()) {
            return false;
        }

        return true;
    }
