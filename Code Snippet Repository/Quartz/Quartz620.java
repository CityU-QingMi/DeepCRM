    public long getLong(String key) {
        Object obj = get(key);
    
        try {
            if(obj instanceof Long)
                return ((Long) obj).longValue();
            return Long.parseLong((String)obj);
        } catch (Exception e) {
            throw new ClassCastException("Identified object is not a Long.");
        }
    }
