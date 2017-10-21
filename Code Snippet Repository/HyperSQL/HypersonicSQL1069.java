    public Object[] getNewRowData(Session session) {

        Object[] data = new Object[columnCount];
        int      i;

        if (hasDefaultValues) {
            for (i = 0; i < columnCount; i++) {
                Expression def = colDefaults[i];

                if (def != null) {
                    data[i] = def.getValue(session, colTypes[i]);
                }
            }
        }

        return data;
    }
