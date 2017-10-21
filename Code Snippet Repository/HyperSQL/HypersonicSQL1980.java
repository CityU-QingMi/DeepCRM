    public HsqlArrayList(int initialCapacity, boolean minimize) {

//        reporter.initCounter++;
        if (initialCapacity < DEFAULT_INITIAL_CAPACITY) {
            initialCapacity = DEFAULT_INITIAL_CAPACITY;
        }

        elementData     = new Object[initialCapacity];
        minimizeOnClear = minimize;
    }
