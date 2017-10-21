    public V get(K k) {
        V v = this.eden.get(k);
        if (v == null) {
            v = this.longterm.get(k);
            if (v != null) {
                this.eden.put(k, v);
            }
        }
        return v;
    }
