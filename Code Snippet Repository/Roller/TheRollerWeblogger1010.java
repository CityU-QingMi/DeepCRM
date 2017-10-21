    public void testAutoFormatPlugin() {
        
        PluginManager pmgr = WebloggerFactory.getWeblogger().getPluginManager();
        
        // setup test comment
        WeblogEntryComment comment = new WeblogEntryComment();
        comment.setContent(convertLinesStart); 
        comment.setPlugins("AutoFormat Plugin");
        
        // reformat
        String output = pmgr.applyCommentPlugins(comment, comment.getContent());
        
        // make sure it turned out how we planned
        assertEquals(convertLinesFormatted, output);        
    }
