    public void addParameter(String key, Object value) {
        // don't use the default implementation of addParameter,
        // instead, include tag requires that each parameter be a list of objects,
        // just like the HTTP servlet interfaces are (String[])
        if (value != null) {
            List currentValues = (List) parameters.get(key);

            if (currentValues == null) {
                currentValues = new ArrayList();
                parameters.put(key, currentValues);
            }

            currentValues.add(value);
        }
    }
