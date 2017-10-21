    protected final void cacheClear(Session session) {

        int i = sysTables.length;

        while (i-- > 0) {
            Table t = sysTables[i];

            if (t != null) {
                t.clearAllData(session);
            }
        }
    }
