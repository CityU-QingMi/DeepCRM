    protected Properties convertToProperty(Map<?, ?> data) throws IOException {
        Properties properties = new Properties();
        
        for (Iterator<?> entryIter = data.entrySet().iterator(); entryIter.hasNext();) {
            Map.Entry<?, ?> entry = (Map.Entry<?, ?>)entryIter.next();
            
            Object key = entry.getKey();
            Object val = (entry.getValue() == null) ? "" : entry.getValue();
            
            if(!(key instanceof String)) {
                throw new IOException("JobDataMap keys/values must be Strings " 
                        + "when the 'useProperties' property is set. " 
                        + " offending Key: " + key);
            }
            
            if(!(val instanceof String)) {
                throw new IOException("JobDataMap values must be Strings " 
                        + "when the 'useProperties' property is set. " 
                        + " Key of offending value: " + key);
            }
            
            properties.put(key, val);
        }
        
        return properties;
    }
