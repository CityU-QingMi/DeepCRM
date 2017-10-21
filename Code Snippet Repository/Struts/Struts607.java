    protected void mergeRequestParameters(String value, Map<String, Object> parameters, Map<String, Object> contextParameters) {

        Map<String, Object> mergedParams = new LinkedHashMap<>(contextParameters);

        // Merge contextParameters (from current request) with parameters specified in value attribute
        // eg. value="someAction.action?id=someId&venue=someVenue"
        // where the parameters specified in value attribute takes priority.

        if (StringUtils.contains(value, "?")) {
            String queryString = value.substring(value.indexOf("?") + 1);

            mergedParams = urlHelper.parseQueryString(queryString, false);
            for (Map.Entry<String, Object> entry : contextParameters.entrySet()) {
                if (!mergedParams.containsKey(entry.getKey())) {
                    mergedParams.put(entry.getKey(), entry.getValue());
                }
            }
        }

        // Merge parameters specified in value attribute
        // eg. value="someAction.action?id=someId&venue=someVenue"
        // with parameters specified though param tag
        // eg. <param name="id" value="%{'someId'}" />
        // where parameters specified through param tag takes priority.

        for (Map.Entry<String, Object> entry : mergedParams.entrySet()) {
            if (!parameters.containsKey(entry.getKey())) {
                parameters.put(entry.getKey(), entry.getValue());
            }
        }
    }
