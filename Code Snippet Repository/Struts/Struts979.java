    public Map<String, Object> parseQueryString(String queryString, boolean forceValueArray) {
        Map<String, Object> queryParams = new LinkedHashMap<String, Object>();
        if (queryString != null) {
            String[] params = queryString.split("&");
            for (String param : params) {
                if (param.trim().length() > 0) {
                    String[] tmpParams = param.split("=");
                    String paramName = null;
                    String paramValue = "";
                    if (tmpParams.length > 0) {
                        paramName = tmpParams[0];
                    }
                    if (tmpParams.length > 1) {
                        paramValue = tmpParams[1];
                    }
                    if (paramName != null) {
                        paramName = decode(paramName, true);
                        String translatedParamValue = decode(paramValue, true);

                        if (queryParams.containsKey(paramName) || forceValueArray) {
                            // WW-1619 append new param value to existing value(s)
                            Object currentParam = queryParams.get(paramName);
                            if (currentParam instanceof String) {
                                queryParams.put(paramName, new String[]{(String) currentParam, translatedParamValue});
                            } else {
                                String currentParamValues[] = (String[]) currentParam;
                                if (currentParamValues != null) {
                                    List<String> paramList = new ArrayList<String>(Arrays.asList(currentParamValues));
                                    paramList.add(translatedParamValue);
                                    queryParams.put(paramName, paramList.toArray(new String[paramList.size()]));
                                } else {
                                    queryParams.put(paramName, new String[]{translatedParamValue});
                                }
                            }
                        } else {
                            queryParams.put(paramName, translatedParamValue);
                        }
                    }
                }
            }
        }
        return queryParams;
    }
