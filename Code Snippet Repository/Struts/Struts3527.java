    public static Map<String, String[]> ensureParamsAreStringArrays(Map<String, Object> params) {
        Map<String, String[]> result = null;
        if (params != null) {
            result = new LinkedHashMap<String, String[]>(params.size());
            for ( String key : params.keySet() ) {
                Object val = params.get(key);
                if (val instanceof String[]) {
                    result.put(key, (String[]) val);
                } else {
                    result.put(key, new String[]{val.toString()});
                }
            }
        }
        return result;
    }
