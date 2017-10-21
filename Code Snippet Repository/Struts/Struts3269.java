    public Object clean(String ognlPrefix, Object data) throws JSONException {
        if (data == null)
            return null;
        else if (data instanceof List)
            return cleanList(ognlPrefix, data);
        else if (data instanceof Map)
            return cleanMap(ognlPrefix, data);
        else
            return cleanValue(ognlPrefix, data);
    }
