    NodeAVL next(Session session, PersistentStore store, NodeAVL x,
                 int distinctCount) {

        if (x == null) {
            return null;
        }

        if (distinctCount != 0) {
            return findDistinctNode(session, store, x, distinctCount, false);
        }

        while (true) {
            x = next(store, x);

            if (x == null) {
                return x;
            }

            if (session == null) {
                return x;
            }

            Row row = x.getRow(store);

            if (session.database.txManager.canRead(
                    session, store, row, TransactionManager.ACTION_READ,
                    null)) {
                return x;
            }
        }
    }
