    public void sortOrder(Session session) {

        if (orderIndex != null) {
            mainIndex = orderIndex;

            ArraySort.sort(dataTable, 0, size, this);
        }

        reset();
    }
