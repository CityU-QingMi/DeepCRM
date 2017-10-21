    public String formatUpdateTime(String pattern) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            
            return format.format(getUpdateTime());
        } catch (RuntimeException e) {
            mLogger.error("Unexpected exception", e);
        }
        
        return "ERROR: formatting date";
    }
