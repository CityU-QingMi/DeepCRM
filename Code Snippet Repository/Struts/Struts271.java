    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

        HttpParameters parameters = invocation.getInvocationContext().getParameters();

        Map<String, Boolean> includesExcludesMap = getIncludesExcludesMap();

        for (String param : parameters.keySet()) {
            boolean currentAllowed = !isDefaultBlock();

            for (String currRule : includesExcludesMap.keySet()) {
                if (param.startsWith(currRule) &&
                    (param.length() == currRule.length() || isPropertySeparator(param.charAt(currRule.length())))
                ) {
                    currentAllowed = includesExcludesMap.get(currRule);
                }
            }
            if (!currentAllowed) {
                LOG.debug("Removing param: {}", param);
                parameters = parameters.remove(param);
            }
        }

        invocation.getInvocationContext().setParameters(parameters);

        return invocation.invoke();
    }
