    void checkpoint(Session session, boolean defrag) {

        if (filesReadOnly) {
            return;
        }

        if (cache == null) {
            defrag = false;
        } else if (forceDefrag()) {
            defrag = true;
        }

        if (defrag) {
            defrag(session);
        } else {
            checkpoint();
        }
    }
