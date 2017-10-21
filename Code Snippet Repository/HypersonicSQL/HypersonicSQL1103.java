    public int insertSys(Session session, PersistentStore store, Result ins) {

        RowSetNavigator nav   = ins.getNavigator();
        int             count = 0;

        while (nav.next()) {
            insertSys(session, store, nav.getCurrent());

            count++;
        }

        return count;
    }
