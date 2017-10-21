    public boolean contains(Right right) {

        if (isFull) {
            return true;
        }

        if (right.isFull) {
            return false;
        }

        if (!containsRights(isFullSelect, selectColumnSet,
                            right.selectColumnSet, right.isFullSelect)) {
            return false;
        }

        if (!containsRights(isFullInsert, insertColumnSet,
                            right.insertColumnSet, right.isFullInsert)) {
            return false;
        }

        if (!containsRights(isFullUpdate, updateColumnSet,
                            right.updateColumnSet, right.isFullUpdate)) {
            return false;
        }

        if (!containsRights(isFullReferences, referencesColumnSet,
                            right.referencesColumnSet,
                            right.isFullReferences)) {
            return false;
        }

        if (!containsRights(isFullTrigger, triggerColumnSet,
                            right.triggerColumnSet, right.isFullTrigger)) {
            return false;
        }

        if (!isFullDelete && right.isFullDelete) {
            return false;
        }

        return true;
    }
