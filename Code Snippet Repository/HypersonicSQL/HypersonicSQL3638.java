    public int compare(Session session, Object a, Object b, int opType) {

        if (a == b) {
            return 0;
        }

        if (a == null) {
            return -1;
        }

        if (b == null) {
            return 1;
        }

        if (b instanceof String) {
            return session.database.lobManager.compare(collation,
                    (ClobData) a, (String) b);
        }

        return session.database.lobManager.compare(collation, (ClobData) a,
                (ClobData) b);
    }
