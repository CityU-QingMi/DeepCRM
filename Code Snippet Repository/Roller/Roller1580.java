    public String render(final WeblogEntryComment comment, String text) {
        String output = text;
        
        // only do this if comment is HTML
        if ("text/html".equals(comment.getContentType())) {
            LOG.debug("ending value:\n" + output);
            	        
	        // escape html
	        output = Utilities.escapeHTML(output);
	        
	        // just use old utilities method
	        output = Utilities.transformToHTMLSubset(output);
	        LOG.debug("starting value:\n" + text);
        }
                
        return output;
    }
