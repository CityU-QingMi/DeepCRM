    public WeblogRequestMapper() {
        
        this.restricted = new HashSet<String>();
        
        // build roller restricted list
        String restrictList = 
                WebloggerConfig.getProperty("rendering.weblogMapper.rollerProtectedUrls");
        if(restrictList != null && restrictList.trim().length() > 0) {
            String[] restrict = restrictList.split(",");
            this.restricted.addAll(Arrays.asList(restrict));
        }
        
        // add user restricted list
        restrictList = 
                WebloggerConfig.getProperty("rendering.weblogMapper.userProtectedUrls");
        if(restrictList != null && restrictList.trim().length() > 0) {
            String[] restrict = restrictList.split(",");
            this.restricted.addAll(Arrays.asList(restrict));
        }
    }
