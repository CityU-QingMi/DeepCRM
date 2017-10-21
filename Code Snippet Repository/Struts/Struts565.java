    public void copyParams(Map params) {
        stack.push(parameters);
        stack.push(this);
        try {
            for (Object o : params.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                String key = (String) entry.getKey();

                if (key.indexOf('-') >= 0) {
                    // UI component attributes may contain hypens (e.g. data-ajax), but ognl
                    // can't handle that, and there can't be a component property with a hypen
                    // so into the parameters map it goes. See WW-4493
                    parameters.put(key, entry.getValue());
                } else {
                    stack.setValue(key, entry.getValue());
                }
            }
        } finally {
            stack.pop();
            stack.pop();
        }
    }
