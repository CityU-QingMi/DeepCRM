    public void reset() {

        super.reset();

        if (iterator != null) {
            iterator.release();
        }

        iterator = mainIndex.firstRow((Session) session, store, 0, null);
    }
