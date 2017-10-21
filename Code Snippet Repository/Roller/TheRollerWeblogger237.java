    public String applyCommentPlugins(WeblogEntryComment comment, String text) {
        
        if(comment == null || text == null) {
            throw new IllegalArgumentException("comment cannot be null");
        }
        
        String content = text;
        
        if (commentPlugins.size() > 0) {
            for (WeblogEntryCommentPlugin plugin : commentPlugins) {
                if(comment.getPlugins() != null &&
                        comment.getPlugins().contains(plugin.getId())) {
                    log.debug("Invoking comment plugin "+plugin.getId());
                    content = plugin.render(comment, content);
                }
            }
        }
        
        return content;
    }
