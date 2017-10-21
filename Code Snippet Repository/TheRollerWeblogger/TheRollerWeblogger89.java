    public String getEntryEditURL(String weblogHandle,
                                               String entryId,
                                               boolean absolute) {

        StringBuilder url = new StringBuilder();
        
        if(absolute) {
            url.append(WebloggerRuntimeConfig.getAbsoluteContextURL());
        } else {
            url.append(WebloggerRuntimeConfig.getRelativeContextURL());
        }
        
        url.append("/roller-ui/authoring/entryEdit.rol");
        
        Map params = new HashMap();
        params.put("weblog", weblogHandle);
        params.put("bean.id", entryId);
        
        return url.toString() + URLUtilities.getQueryString(params);
    }
