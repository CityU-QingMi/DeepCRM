    public V put(K key, V value) {
        synchronized (this) {
            if (session == null) {
                session = request.getSession(true);
            }
        }
        synchronized (session.getId().intern()) {
            V oldValue = get(key);
            entries = null;
            session.setAttribute(key.toString(), value);
            return oldValue;
        }
    }
