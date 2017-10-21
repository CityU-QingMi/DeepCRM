    public String intercept(ActionInvocation ai) throws Exception {
        HttpParameters parameters = ai.getInvocationContext().getParameters();
        Map<String, Parameter> newParams = new HashMap<>();

        for (String name : parameters.keySet()) {
            if (name.startsWith("__multiselect_")) {
                String key = name.substring("__multiselect_".length());

                // is this multi-select box submitted?
                if (!parameters.contains(key)) {
                    // if not, let's be sure to default the value to an empty string array
                    newParams.put(key, new Parameter.Request(key, new String[0]));
                }

                parameters = parameters.remove(name);
            }
        }

        ai.getInvocationContext().getParameters().appendAll(newParams);

        return ai.invoke();
    }
