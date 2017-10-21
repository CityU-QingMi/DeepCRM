    synchronized public void push(boolean isRoutine) {

        if (rowStoreListStack == null) {
            rowStoreListStack = new HsqlDeque();
        }

        Object[] array = rowStoreMapStatement.toArray();

        rowStoreListStack.add(array);
        rowStoreMapStatement.clear();

        if (isRoutine) {
            array = rowStoreMapRoutine.toArray();

            rowStoreListStack.add(array);
            rowStoreMapRoutine.clear();
        }
    }
