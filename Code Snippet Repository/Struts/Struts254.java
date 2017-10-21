    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(size());
        for (Map.Entry<Object, Object> entry : delegate.entrySet()) {
            Object key = dereferenceKey(entry.getKey());
            Object value = dereferenceValue(entry.getValue());

            // don't persist gc'ed entries.
            if (key != null && value != null) {
                out.writeObject(key);
                out.writeObject(value);
            }
        }
        out.writeObject(null);
    }
