    public Set entrySet() {
        if (entries == null) {
            entries = new HashSet<>();

            Enumeration enumeration = request.getAttributeNames();

            while (enumeration.hasMoreElements()) {
                final String key = enumeration.nextElement().toString();
                final Object value = request.getAttribute(key);
                entries.add(new Entry() {
                    public boolean equals(Object obj) {
                        if (!(obj instanceof Entry)) {
                            return false;
                        }
                        Entry entry = (Entry) obj;

                        return ((key == null) ? (entry.getKey() == null) : key.equals(entry.getKey())) && ((value == null) ? (entry.getValue() == null) : value.equals(entry.getValue()));
                    }

                    public int hashCode() {
                        return ((key == null) ? 0 : key.hashCode()) ^ ((value == null) ? 0 : value.hashCode());
                    }

                    public Object getKey() {
                        return key;
                    }

                    public Object getValue() {
                        return value;
                    }

                    public Object setValue(Object obj) {
                        request.setAttribute(key, obj);

                        return value;
                    }
                });
            }
        }

        return entries;
    }
