    public String getString(String key) {
        
        try {
            return bundle.getString(key);
        } catch (Exception e) {
            // send a warning in the logs
            LOG.warn("Error getting key " + key);
            return key;
        }
    }
