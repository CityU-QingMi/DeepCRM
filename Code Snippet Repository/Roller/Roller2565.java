    public Object getCategories(String blogid, String userid, String password)
    throws Exception {
        
        mLogger.debug("getCategories() Called =====[ SUPPORTED ]=====");
        mLogger.debug("     BlogId: " + blogid);
        mLogger.debug("     UserId: " + userid);
        
        Weblog website = validate(blogid, userid,password);
        Weblogger roller = WebloggerFactory.getWeblogger();
        try {
            Hashtable result = new Hashtable();
            WeblogEntryManager weblogMgr = roller.getWeblogEntryManager();
            List<WeblogCategory> cats = weblogMgr.getWeblogCategories(website);
            for (WeblogCategory category : cats) {
                result.put(category.getName(),
                        createCategoryStruct(category, userid));
            }
            return result;
        } catch (Exception e) {
            String msg = "ERROR in MetaWeblogAPIHandler.getCategories";
            mLogger.error(msg,e);
            throw new XmlRpcException(UNKNOWN_EXCEPTION, msg);
        }
    }
