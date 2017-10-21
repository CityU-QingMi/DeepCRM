    protected HttpParameters createParametersForContext() {
        HttpParameters parentParams = null;

        if (!ignoreContextParams) {
            parentParams = new ActionContext(getStack().getContext()).getParameters();
        }

        HttpParameters.Builder builder = HttpParameters.create();
        if (parentParams != null) {
            builder = builder.withParent(parentParams);
        }

        if (parameters != null) {
            Map<String, String[]> params = new HashMap<>();
            for (Object o : parameters.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                String key = (String) entry.getKey();
                Object val = entry.getValue();
                if (val.getClass().isArray() && String.class == val.getClass().getComponentType()) {
                    params.put(key, (String[])val);
                } else {
                    params.put(key, new String[]{val.toString()});
                }
            }
            builder = builder.withExtraParams(params);
        }
        return builder.build();
    }
