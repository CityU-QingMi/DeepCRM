    public String generateKey(WeblogFeedRequest feedRequest) {
        
        StringBuilder key = new StringBuilder();
        
        key.append(CACHE_ID).append(":");
        key.append("feed/");
        key.append(feedRequest.getWeblogHandle());
        
        key.append("/").append(feedRequest.getType());
        key.append("/").append(feedRequest.getFormat());
        
        if (feedRequest.getTerm() != null) {
            key.append("/search/").append(feedRequest.getTerm());
        }
        
        if(feedRequest.getWeblogCategoryName() != null) {
            String cat = feedRequest.getWeblogCategoryName();
            try {
                cat = URLEncoder.encode(cat, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                // should never happen, utf-8 is always supported
            }
            
            key.append("/").append(cat);
        }
        
        if(feedRequest.getLocale() != null) {
            key.append("/").append(feedRequest.getLocale());
        }
        
        if(feedRequest.isExcerpts()) {
            key.append("/excerpts");
        }
        
        if(feedRequest.getTags() != null && feedRequest.getTags().size() > 0) {
          String[] tags = new String[feedRequest.getTags().size()];
          new TreeSet(feedRequest.getTags()).toArray(tags);
          key.append("/tags/").append(Utilities.stringArrayToString(tags,"+"));
        }       
        
        return key.toString();
    }
