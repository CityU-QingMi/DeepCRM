    public HsqlArrayList(int initialCapacity) {

//        reporter.initCounter++;
        if (initialCapacity < 0) {
            throw new NegativeArraySizeException(
                "Invalid initial capacity given");
        }

        if (initialCapacity < DEFAULT_INITIAL_CAPACITY) {
            initialCapacity = DEFAULT_INITIAL_CAPACITY;
        }

        elementData = new Object[initialCapacity];
    }
