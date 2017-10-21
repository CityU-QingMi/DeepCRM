    public Object getPost(String postid, String userid, String password)
    throws Exception {
        
        mLogger.debug("getPost() Called =========[ SUPPORTED ]=====");
        mLogger.debug("     PostId: " + postid);
        mLogger.debug("     UserId: " + userid);
        
        Weblogger roller = WebloggerFactory.getWeblogger();
        WeblogEntryManager weblogMgr = roller.getWeblogEntryManager();
        WeblogEntry entry = weblogMgr.getWeblogEntry(postid);
        
        if (entry == null) {
            throw new XmlRpcException(INVALID_POSTID, INVALID_POSTID_MSG);
        }
        validate(entry.getWebsite().getHandle(), userid, password);
        
        try {
            return createPostStruct(entry, userid);
        } catch (Exception e) {
            String msg = "ERROR in MetaWeblogAPIHandler.getPost";
            mLogger.error(msg, e);
            throw new XmlRpcException(UNKNOWN_EXCEPTION, msg);
        }
    }
