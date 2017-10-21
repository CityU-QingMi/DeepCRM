    public OrderedLongKeyHashMap(int initialCapacity,
                                 boolean hasThirdValue)
                                 throws IllegalArgumentException {

        super(initialCapacity, BaseHashMap.longKeyOrValue,
              BaseHashMap.objectKeyOrValue, false);

        objectKeyTable   = new Object[objectValueTable.length];
        isTwoObjectValue = true;
        isList           = true;

        if (hasThirdValue) {
            objectValueTable2 = new Object[objectValueTable.length];
        }

        minimizeOnEmpty = true;
    }
