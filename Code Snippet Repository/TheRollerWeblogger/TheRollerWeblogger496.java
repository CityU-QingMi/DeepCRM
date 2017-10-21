        protected String createURL(String url, Map params) {
            List tags = feedRequest.getTags();
            if(tags != null && tags.size() > 0) {
                params.put("tags", URLUtilities.getEncodedTagsString(tags));
            }
            String category = feedRequest.getWeblogCategoryName();
            if(category != null && category.trim().length() > 0) {
                params.put("cat", URLUtilities.encode(category));
            }  
            if(feedRequest.isExcerpts()) {
                params.put("excerpts", "true");
            }            
            return super.createURL(url, params);
        }
