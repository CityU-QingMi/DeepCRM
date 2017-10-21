    @Override
    public String execute() {
        
        // setup array of configured plugins
        if (!StringUtils.isEmpty(WebloggerRuntimeConfig.getProperty("users.comments.plugins"))) {
            setCommentPlugins(StringUtils.split(WebloggerRuntimeConfig.getProperty("users.comments.plugins"), ","));
        }
        
        return SUCCESS;
    }
