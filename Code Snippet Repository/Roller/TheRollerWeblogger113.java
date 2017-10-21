    public String getWeblogFeedURL(Weblog weblog,
                                                String locale,
                                                String type,
                                                String format,
                                                String category,
                                                String term,
                                                List tags,
                                                boolean excerpts,
                                                boolean absolute) {
        
        if(weblog == null) {
            return null;
        }
        
        StringBuilder url = new StringBuilder();
        
        url.append(getWeblogURL(weblog, locale, absolute));
        url.append("feed/").append(type).append("/").append(format);
        
        Map params = new HashMap();
        if(category != null && category.trim().length() > 0) {
            params.put("cat", URLUtilities.encode(category));
        }
        if(tags != null && tags.size() > 0) {
          params.put("tags", URLUtilities.getEncodedTagsString(tags));
        }
        if(term != null && term.trim().length() > 0) {
            params.put("q", URLUtilities.encode(term.trim()));
        }
        if(excerpts) {
            params.put("excerpts", "true");
        }
        
        return url.toString() + URLUtilities.getQueryString(params);
    }
