    public float getFloat(String key) {
        Object obj = get(key);
    
        try {
            if(obj instanceof Float)
                return ((Float) obj).floatValue();
            return Float.parseFloat((String)obj);
        } catch (Exception e) {
            throw new ClassCastException("Identified object is not a Float.");
        }
    }
