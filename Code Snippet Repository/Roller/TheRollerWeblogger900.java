    public boolean deletePost(String appkey, String postid, String userid,
            String password, boolean publish) throws Exception {
        
        mLogger.debug("deletePost() Called =====[ SUPPORTED ]=====");
        mLogger.debug("     Appkey: " + appkey);
        mLogger.debug("     PostId: " + postid);
        mLogger.debug("     UserId: " + userid);
        
        Weblogger roller = WebloggerFactory.getWeblogger();
        WeblogEntryManager weblogMgr = roller.getWeblogEntryManager();
        WeblogEntry entry = weblogMgr.getWeblogEntry(postid);
        
        // Return false if entry not found
        if (entry == null) {
            return false;
        }
        
        validate(entry.getWebsite().getHandle(), userid, password);
        
        try {
            // notify cache
            flushPageCache(entry.getWebsite());

            // delete the entry
            weblogMgr.removeWeblogEntry(entry);
            roller.flush();
            
            
        } catch (Exception e) {
            String msg = "ERROR in blogger.deletePost: "+e.getClass().getName();
            mLogger.error(msg,e);
            throw new XmlRpcException(UNKNOWN_EXCEPTION, msg);
        }
        
        return true;
    }
