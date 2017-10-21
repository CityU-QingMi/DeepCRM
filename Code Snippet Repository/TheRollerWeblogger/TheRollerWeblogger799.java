    public String getString(String key, Object[] args) {
        
        try {
            String msg = bundle.getString(key);
            return MessageFormat.format(msg, args);
        } catch (Exception e) {
            // send a warning in the logs
            LOG.warn("Error getting key " + key, e);
            return key;
        }
    }
