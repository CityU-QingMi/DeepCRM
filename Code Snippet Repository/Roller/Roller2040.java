    public String getContent() {
        
        String content = this.pojo.getContent();
        
        // escape content if content-type is text/plain
        if("text/plain".equals(this.pojo.getContentType())) {
            content = StringEscapeUtils.escapeHtml4(content);
        }
        
        // apply plugins
        PluginManager pmgr = WebloggerFactory.getWeblogger().getPluginManager();
        content = pmgr.applyCommentPlugins(this.pojo, content);
        
        // always add rel=nofollow for links
        content = Utilities.addNofollow(content);
        
        return content;
    }
