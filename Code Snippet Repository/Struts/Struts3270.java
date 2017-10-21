    protected Object cleanMap(String ognlPrefix, Object data) throws JSONException {
        Map map = (Map) data;
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry e = (Map.Entry) iter.next();
            String key = (ognlPrefix.length() > 0 ? ognlPrefix + "." : "") + e.getKey();
            if (allow(key)) {
                e.setValue(clean(key, e.getValue()));
            } else {
                LOG.debug("Blocked: {}", key);
                iter.remove();
            }
        }
        return map;
    }
