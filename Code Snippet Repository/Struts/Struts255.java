    private void readObject(ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        in.defaultReadObject();
        int size = in.readInt();
        this.delegate = new ConcurrentHashMap<Object, Object>(size);
        while (true) {
            K key = (K) in.readObject();
            if (key == null) {
                break;
            }
            V value = (V) in.readObject();
            put(key, value);
        }
    }
