    private String getLogNameSuffix()
    {
        // Use display name first
        String log_name = getDisplayName();
        if (StringUtil.isBlank(log_name))
        {
            // try context path
            log_name = getContextPath();
            if (log_name != null)
            {
                // Strip prefix slash
                if (log_name.startsWith("/"))
                {
                    log_name = log_name.substring(1);
                }
            }
            
            if (StringUtil.isBlank(log_name))
            {
                // an empty context path is the ROOT context
                log_name = "ROOT";
            }
        }
        
        // Replace bad characters.
        return '.' + log_name.replaceAll("\\W", "_");
    }
