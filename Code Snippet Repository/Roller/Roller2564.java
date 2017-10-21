    public Object getRecentPosts(String appkey, String blogid, String userid,
            String password, int numposts)
            throws Exception {
        
        mLogger.debug("getRecentPosts() Called ===========[ SUPPORTED ]=====");
        mLogger.debug("     Appkey: " + appkey);
        mLogger.debug("     BlogId: " + blogid);
        mLogger.debug("     UserId: " + userid);
        mLogger.debug("     Number: " + numposts);
        
        Weblog weblog = validate(blogid, userid,password);
        
        try {
            Vector results = new Vector();
            
            Weblogger roller = WebloggerFactory.getWeblogger();
            WeblogEntryManager weblogMgr = roller.getWeblogEntryManager();
            if (weblog != null) {
                WeblogEntrySearchCriteria wesc = new WeblogEntrySearchCriteria();
                wesc.setWeblog(weblog);
                wesc.setEndDate(new Date());
                Map<Date, List<WeblogEntry>> entries = weblogMgr.getWeblogEntryObjectMap(wesc);

                for (List<WeblogEntry> weList : entries.values()) {
                    for (WeblogEntry entry : weList) {
                        Hashtable result = new Hashtable();
                        if (entry.getPubTime() != null) {
                            result.put("dateCreated", entry.getPubTime());
                        }
                        result.put("userid", userid);
                        result.put("postid", entry.getId());
                        result.put("content", entry.getText());
                        results.add(result);
                    }
                }
            }
            return results;
        } catch (Exception e) {
            String msg = "ERROR in BlooggerAPIHander.getRecentPosts";
            mLogger.error(msg,e);
            throw new XmlRpcException(UNKNOWN_EXCEPTION, msg);
        }
    }
