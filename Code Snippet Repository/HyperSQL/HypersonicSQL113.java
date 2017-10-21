    public void materialise(Session session) {

        if (table == null) {
            return;
        }

        if (table.isCorrelated()) {
            table.materialiseCorrelated(session);
        } else {
            table.materialise(session);
        }
    }
