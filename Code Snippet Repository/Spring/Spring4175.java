    Item newStringishItem(final int type, final String value) {
        key2.set(type, value, null, null);
        Item result = get(key2);
        if (result == null) {
            pool.put12(type, newUTF8(value));
            result = new Item(index++, key2);
            put(result);
        }
        return result;
    }
