    public double getDouble(String key) {
        Object obj = get(key);
    
        try {
            if(obj instanceof Double)
                return ((Double) obj).doubleValue();
            return Double.parseDouble((String)obj);
        } catch (Exception e) {
            throw new ClassCastException("Identified object is not a Double.");
        }
    }
