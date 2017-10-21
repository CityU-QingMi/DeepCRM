    public int compare(Session session, Object a, Object b) {

        if (a == b) {
            return 0;
        }

        if (a == null) {
            return -1;
        }

        if (b == null) {
            return 1;
        }

        if (b instanceof BinaryData) {
            return session.database.lobManager.compare((BlobData) a,
                    ((BlobData) b).getBytes());
        }

        return session.database.lobManager.compare((BlobData) a, (BlobData) b);
    }
