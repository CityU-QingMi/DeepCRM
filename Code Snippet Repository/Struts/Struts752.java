    public String intercept(ActionInvocation ai) throws Exception {
        HttpParameters parameters = ai.getInvocationContext().getParameters();
        Map<String, Parameter> extraParams = new HashMap<>();

        for (String name : parameters.keySet()) {
            if (name.startsWith("__checkbox_")) {
                String checkboxName = name.substring("__checkbox_".length());

                Parameter value = parameters.get(name);
                parameters = parameters.remove(name);
                if (value.isMultiple()) {
              	    LOG.debug("Bypassing automatic checkbox detection due to multiple checkboxes of the same name: {}", name);
                    continue;
                }

                // is this checkbox checked/submitted?
                if (!parameters.contains(checkboxName)) {
                    // if not, let's be sure to default the value to false
                    extraParams.put(checkboxName, new Parameter.Request(checkboxName, uncheckedValue));
                }
            }
        }


        ai.getInvocationContext().getParameters().appendAll(extraParams);

        return ai.invoke();
    }
