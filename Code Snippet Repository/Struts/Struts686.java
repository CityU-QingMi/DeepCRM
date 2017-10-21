    public V remove(Object key) {
        if (session == null) {
            return null;
        }

        synchronized (session.getId().intern()) {
            entries = null;

            V value = get(key);
            session.removeAttribute(key.toString());

            return value;
        }
    }
