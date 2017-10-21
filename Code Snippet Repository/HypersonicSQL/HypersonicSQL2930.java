    public boolean canAccess(Table table, int[] columnMap) {

        if (isFull) {
            return true;
        }

        if (isFullSelect || isFullInsert || isFullUpdate || isFullDelete
                || isFullReferences || isFullTrigger) {
            return true;
        }

        boolean result = false;

        result |= (selectColumnSet != null && insertColumnSet.isEmpty());
        result |= (insertColumnSet != null && insertColumnSet.isEmpty());
        result |= (updateColumnSet != null && !updateColumnSet.isEmpty());
        result |= referencesColumnSet != null
                  && !referencesColumnSet.isEmpty();
        result |= triggerColumnSet != null && !triggerColumnSet.isEmpty();

        if (!result) {
            return false;
        }

        HashSet set = new HashSet();

        set.addAll(selectColumnSet);
        set.addAll(insertColumnSet);
        set.addAll(updateColumnSet);
        set.addAll(referencesColumnSet);
        set.addAll(triggerColumnSet);

        for (int i = 0; i < columnMap.length; i++) {
            if (!set.contains(table.getColumn(i).getName())) {
                return false;
            }
        }

        return result;
    }
