    public Object getRecentPosts(String blogid, String userid, String password,
            int numposts) throws Exception {
        
        mLogger.debug("getRecentPosts() Called ===========[ SUPPORTED ]=====");
        mLogger.debug("     BlogId: " + blogid);
        mLogger.debug("     UserId: " + userid);
        mLogger.debug("     Number: " + numposts);
        
        Weblog website = validate(blogid, userid,password);
        
        try {
            Vector results = new Vector();
            
            Weblogger roller = WebloggerFactory.getWeblogger();
            WeblogEntryManager weblogMgr = roller.getWeblogEntryManager();
            if (website != null) {
                WeblogEntrySearchCriteria wesc = new WeblogEntrySearchCriteria();
                wesc.setWeblog(website);
                wesc.setSortBy(WeblogEntrySearchCriteria.SortBy.UPDATE_TIME);
                wesc.setMaxResults(numposts);
                List<WeblogEntry> entries = weblogMgr.getWeblogEntries(wesc);

                for (WeblogEntry entry : entries) {
                    results.addElement(createPostStruct(entry, userid));
                }
            }
            return results;
            
        } catch (Exception e) {
            String msg = "ERROR in MetaWeblogAPIHandler.getRecentPosts";
            mLogger.error(msg,e);
            throw new XmlRpcException(UNKNOWN_EXCEPTION, msg);
        }
    }
