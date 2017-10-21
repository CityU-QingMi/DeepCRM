    public Object getValue(Session session) {

        Object[] data = ValuePool.emptyObjectArray;

        if (nodes.length > 0) {
            data = new Object[nodes.length];

            for (int i = 0; i < nodes.length; i++) {
                Expression e = nodes[i];

                if (e != null) {
                    data[i] = e.getValue(session, e.dataType);
                }
            }
        }

        return getValue(session, data);
    }
