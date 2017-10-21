    public boolean containsUpdatedRow(Row row, Row refRow, int[] keys) {

        int lookup = list.getLookup(refRow.getId());

        if (lookup > -1) {
            return true;
        }

        Object[] rowData = row.getData();

        outerloop:
        for (int i = 0; i < size; i++) {
            Row oldRow = (Row) list.getValueByIndex(i);

            if (oldRow.getTable() != row.getTable()) {
                continue;
            }

            Type[]   types = row.getTable().getColumnTypes();
            Object[] data  = (Object[]) list.getSecondValueByIndex(i);

            for (int j = 0; j < keys.length; j++) {
                int pos = keys[j];

                if (types[pos].compare(session, rowData[pos], data[pos])
                        != 0) {
                    continue outerloop;
                }
            }

            return true;
        }

        return false;
    }
