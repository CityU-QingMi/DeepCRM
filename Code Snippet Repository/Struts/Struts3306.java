    protected void process(Object object, Method method) throws JSONException {
        this.stack.push(object);

        if (object instanceof Class) {
            this.string(object);
        } else if (object instanceof Boolean) {
            this.bool((Boolean) object);
        } else if (object instanceof Number) {
            this.add(object);
        } else if (object instanceof String) {
            this.string(object);
        } else if (object instanceof Character) {
            this.string(object);
        } else if (object instanceof Map) {
            this.map((Map) object, method);
        } else if (object.getClass().isArray()) {
            this.array(object, method);
        } else if (object instanceof Iterable) {
            this.array(((Iterable) object).iterator(), method);
        } else if (object instanceof Date) {
            this.date((Date) object, method);
        } else if (object instanceof Calendar) {
            this.date(((Calendar) object).getTime(), method);
        } else if (object instanceof Locale) {
            this.string(object);
        } else if (object instanceof Enum) {
            this.enumeration((Enum) object);
        } else {
            processCustom(object, method);
        }

        this.stack.pop();
    }
