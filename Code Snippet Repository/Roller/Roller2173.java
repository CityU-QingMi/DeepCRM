    protected String createURL(String url, Map params) {
        String category = feedRequest.getWeblogCategoryName();
        if(category != null && category.trim().length() > 0) {
            params.put("cat", URLUtilities.encode(category));
        }
        String term = feedRequest.getTerm();
        if(term != null && term.trim().length() > 0) {
            params.put("q", URLUtilities.encode(term.trim()));
        }     
        List tags = feedRequest.getTags();
        if(tags != null && tags.size() > 0) {
            params.put("tags", URLUtilities.getEncodedTagsString(tags));
        }
        if(feedRequest.isExcerpts()) {
            params.put("excerpts", "true");
        }        
        return super.createURL(url, params);
    }
