    public void unionAll(Session session, RowSetNavigatorData other) {

        mainIndex = fullIndex;

        other.reset();

        while (other.next()) {
            Object[] currentData = other.getCurrent();

            add(currentData);
        }

        reset();
    }
