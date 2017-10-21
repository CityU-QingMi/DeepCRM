    public String getWeblogPageURL(Weblog weblog,
                                                String locale,
                                                String pageLink,
                                                String entryAnchor,
                                                String category,
                                                String dateString,
                                                List tags,
                                                int pageNum,
                                                boolean absolute) {
        
        if(weblog == null) {
            return null;
        }
        
        StringBuilder pathinfo = new StringBuilder();
        Map params = new HashMap();
        
        pathinfo.append(getWeblogURL(weblog, locale, absolute));
        
        if(pageLink != null) {
            pathinfo.append("page/").append(pageLink);
            
            // for custom pages we only allow query params
            if(dateString != null) {
                params.put("date", dateString);
            }
            if(category != null) {
                params.put("cat", URLUtilities.encode(category));
            }
            if(tags != null && tags.size() > 0) {
                params.put("tags", URLUtilities.getEncodedTagsString(tags));
            }
            if(pageNum > 0) {
                params.put("page", Integer.toString(pageNum));
            }
        } else {
            // if there is no page link then this is just a typical collection url
            return getWeblogCollectionURL(weblog, locale, category, dateString, tags, pageNum, absolute);
        }
        
        return pathinfo.toString() + URLUtilities.getQueryString(params);
    }
