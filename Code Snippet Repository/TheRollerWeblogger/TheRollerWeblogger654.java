    public void setParameters(Map<String, String[]> parameters) {
        this.params = parameters;
        
        if (log.isDebugEnabled()) {
            log.debug("Parameter map:");

            for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
                log.debug(entry.getKey() + " = " + entry.getValue());
            }
        }
    }
