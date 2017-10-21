    public boolean containsTransientData() {
        if (!getAllowsTransientData()) { // short circuit...
            return false;
        }
    
        String[] keys = getKeys();
        for (int i = 0; i < keys.length; i++) {
            Object o = super.get(keys[i]);
            if (!(o instanceof Serializable)) {
                return true;
            }
        }
    
        return false;
    }
