    @SuppressWarnings("")
    public V get(Object key) {
        if (session == null) {
            return null;
        }

        synchronized (session.getId().intern()) {
            return (V) session.getAttribute(key.toString());
        }
    }
