    protected void value(Object object, Method method) throws JSONException {
        if (object == null) {
            this.add("null");
            return;
        }

        if (this.stack.contains(object)) {
            Class clazz = object.getClass();

            // cyclic reference
            if (clazz.isPrimitive() || clazz.equals(String.class)) {
                this.process(object, method);
            } else {
                LOG.debug("Cyclic reference detected on {}", object);
                this.add("null");
            }

            return;
        }

        this.process(object, method);
    }
