    public int compare(Session session, Object a, Object b) {

        if (a == null) {
            return -1;
        }

        if (b == null) {
            return 1;
        }

        return 0;
    }
