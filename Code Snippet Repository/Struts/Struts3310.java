    protected boolean add(String name, Object value, Method method, boolean hasData) throws JSONException {
        if (excludeNullProperties && value == null) {
            return false;
        }
        if (hasData) {
            this.add(',');
        }
        this.add('"');
        this.add(name);
        this.add("\":");
        this.value(value, method);
        return true;
    }
