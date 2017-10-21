    public char getChar(String key) {
        Object obj = get(key);
    
        try {
            if(obj instanceof Character)
                return ((Character) obj).charValue();
            return ((String)obj).charAt(0);
        } catch (Exception e) {
            throw new ClassCastException("Identified object is not a Character.");
        }
    }
