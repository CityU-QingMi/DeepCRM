    private void insertArrayValues(Session session, PersistentStore store) {

        Object[][] array = new Object[nodes.length][];

        for (int i = 0; i < array.length; i++) {
            Object[] values = (Object[]) nodes[i].getValue(session);

            if (values == null) {
                values = ValuePool.emptyObjectArray;
            }

            array[i] = values;
        }

        for (int i = 0; ; i++) {
            boolean  isRow = false;
            Object[] data  = new Object[nodeDataTypes.length];

            for (int arrayIndex = 0; arrayIndex < array.length; arrayIndex++) {
                if (i < array[arrayIndex].length) {
                    data[arrayIndex] = array[arrayIndex][i];
                    isRow            = true;
                }
            }

            if (!isRow) {
                break;
            }

            if (ordinality) {
                data[nodes.length] = ValuePool.getInt(i + 1);
            }

            Row row = (Row) store.getNewCachedObject(session, data, false);

            store.indexRow(session, row);
        }
    }
