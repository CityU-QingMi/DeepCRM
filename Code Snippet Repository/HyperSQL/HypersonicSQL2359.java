    public boolean addUpdate(Row row, Object[] data, int[] columnMap) {

        int lookup = list.getLookup(row.getId());

        if (lookup == -1) {
            return false;
        }

        list.put(row.getId(), row, data);
        list.setThirdValueByIndex(lookup, columnMap);

        return true;
    }
