    public void putAll(IntKeyHashMap other) {

        Iterator it = other.keySet().iterator();

        while (it.hasNext()) {
            int key = it.nextInt();

            put(key, other.get(key));
        }
    }
