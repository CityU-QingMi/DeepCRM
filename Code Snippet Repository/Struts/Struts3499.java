    public Set<Entry<String, Object>> entrySet() {
        synchronized (session) {
            if (entries == null) {
                entries = new HashSet<Entry<String, Object>>();

                Enumeration enumeration = session.getAttributeNames();

                while (enumeration.hasMoreElements()) {
                    final String key = enumeration.nextElement().toString();
                    final Object value = session.getAttribute(key);
                    entries.add(new Entry<String, Object>() {
                        public boolean equals(Object obj) {
                            Map.Entry entry = (Map.Entry) obj;

                            return ((key == null) ? (entry.getKey() == null)
                                    : key.equals(entry.getKey()))
                                    && ((value == null) ? (entry.getValue() == null)
                                            : value.equals(entry.getValue()));
                        }

                        public int hashCode() {
                            return ((key == null) ? 0 : key.hashCode())
                                    ^ ((value == null) ? 0 : value.hashCode());
                        }

                        public String getKey() {
                            return key;
                        }

                        public Object getValue() {
                            return value;
                        }

                        public Object setValue(Object obj) {
                            session.setAttribute(key, obj);

                            return value;
                        }
                    });
                }
            }
        }

        return entries;
    }
