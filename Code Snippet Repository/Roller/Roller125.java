    public String getWeblogCollectionURL(Weblog weblog,
                                                      String locale,
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
        
        String cat;
        if("root".equals(category)) {
            cat = null;
        } else {
            cat = category;
        }
        
        if(cat != null && dateString == null) {
            pathinfo.append("category/").append(URLUtilities.encodePath(cat));
            
        } else if(dateString != null && cat == null) {
            pathinfo.append("date/").append(dateString);  
        
        } else if(tags != null && tags.size() > 0) {
            pathinfo.append("tags/").append(URLUtilities.getEncodedTagsString(tags));
        } else {
            if(dateString != null) {
                params.put("date", dateString);
            }
            if(cat != null) {
                params.put("cat", URLUtilities.encode(cat));
            }
        }

        if(pageNum > 0) {
            params.put("page", Integer.toString(pageNum));
        }
        
        return pathinfo.toString() + URLUtilities.getQueryString(params);
    }
