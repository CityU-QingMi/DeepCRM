    public boolean getBoolean(String key) {
        Object obj = get(key);
    
        try {
            if(obj instanceof Boolean)
                return ((Boolean) obj).booleanValue();
            return Boolean.parseBoolean((String)obj);
        } catch (Exception e) {
            throw new ClassCastException("Identified object is not a Boolean.");
        }
    }
