    public boolean containsDeletedRow(Row refRow) {

        int lookup = list.getLookup(refRow.getId());

        if (lookup == -1) {
            return false;
        }

        Object[] currentData = (Object[]) list.getSecondValueByIndex(lookup);

        return currentData == null;
    }
