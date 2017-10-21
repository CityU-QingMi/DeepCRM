    public String getString(String key, List args) {
        
        try {
            String msg = bundle.getString(key);
            return MessageFormat.format(msg, args.toArray());
        } catch (Exception e) {
            // send a warning in the logs
            LOG.warn("Error getting key " + key, e);
            return key;
        }
    }
