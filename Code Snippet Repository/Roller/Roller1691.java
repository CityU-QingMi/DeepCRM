    protected Properties getTaskProperties() {
        
        String prefix = "tasks."+this.getName()+".";
        
        Properties taskProps = new Properties();
        
        String key = null;
        Enumeration keys = WebloggerConfig.keys();
        while(keys.hasMoreElements()) {
            key = (String) keys.nextElement();
            
            if(key.startsWith(prefix)) {
                taskProps.setProperty(key.substring(prefix.length()), 
                        WebloggerConfig.getProperty(key));
            }
        }
        
        // special addition for clientId property that applies to all tasks
        taskProps.setProperty("clientId", WebloggerConfig.getProperty("tasks.clientId"));
        
        return taskProps;
    }
