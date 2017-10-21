    public URLBean addParameter(String name, Object value) {
        if (params == null) {
            params = new HashMap<String, String>();
        }

        if (value == null) {
            params.remove(name);
        } else {
            params.put(name, value.toString());
        }

        return this;
    }
