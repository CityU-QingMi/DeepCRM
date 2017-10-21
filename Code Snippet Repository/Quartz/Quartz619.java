    public int getInt(String key) {
        Object obj = get(key);
    
        try {
            if(obj instanceof Integer)
                return ((Integer) obj).intValue();
            return Integer.parseInt((String)obj);
        } catch (Exception e) {
            throw new ClassCastException("Identified object is not an Integer.");
        }
    }
