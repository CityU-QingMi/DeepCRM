    public void removeTransactionInfo(long id) {

        rowActionMap.getWriteLock().lock();

        try {
            RowAction action = (RowAction) rowActionMap.get(id);

            synchronized (action) {

                // remove only if not changed
                if (action.type == RowActionBase.ACTION_NONE) {
                    rowActionMap.remove(id);
                }
            }
        } finally {
            rowActionMap.getWriteLock().unlock();
        }
    }
