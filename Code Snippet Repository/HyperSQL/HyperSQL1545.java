    public void update(Object[] oldData, Object[] newData) {

        if (isSimpleAggregate) {
            return;
        }

        RowIterator it = groupIndex.findFirstRow((Session) session, store,
            oldData);

        if (it.next()) {
            it.removeCurrent();
            it.release();

            size--;

            add(newData);
        }
    }
