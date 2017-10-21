    public void putAll(LongKeyHashMap other) {

        Iterator it = other.keySet().iterator();

        while (it.hasNext()) {
            long key = it.nextLong();

            put(key, other.get(key));
        }
    }
