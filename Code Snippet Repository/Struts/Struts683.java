    @SuppressWarnings("")
    public Set<java.util.Map.Entry<K, V>> entrySet() {
        if (session == null) {
            return Collections.emptySet();
        }

        synchronized (session.getId().intern()) {
            if (entries == null) {
                entries = new HashSet<>();

                Enumeration<?> enumeration = session.getAttributeNames();

                while (enumeration.hasMoreElements()) {
                    final String key = enumeration.nextElement().toString();
                    final Object value = session.getAttribute(key);
                    entries.add(new Map.Entry<K, V>() {
                        public boolean equals(Object obj) {
                            if (!(obj instanceof Map.Entry)) {
                                return false;
                            }
                            Map.Entry<K, V> entry = (Map.Entry<K, V>) obj;

                            return ((key == null) ? (entry.getKey() == null) : key.equals(entry.getKey())) && ((value == null) ? (entry.getValue() == null) : value.equals(entry.getValue()));
                        }

                        public int hashCode() {
                            return ((key == null) ? 0 : key.hashCode()) ^ ((value == null) ? 0 : value.hashCode());
                        }

                        public K getKey() {
                            return (K) key;
                        }

                        public V getValue() {
                            return (V) value;
                        }

                        public V setValue(Object obj) {
                            session.setAttribute(key, obj);

                            return (V) value;
                        }
                    });
                }
            }
        }

        return entries;
    }
