    public boolean editPost(String appkey, String postid, String userid,
            String password, String content, boolean publish)
            throws Exception {
        
        mLogger.debug("editPost() Called ========[ SUPPORTED ]=====");
        mLogger.debug("     Appkey: " + appkey);
        mLogger.debug("     PostId: " + postid);
        mLogger.debug("     UserId: " + userid);
        mLogger.debug("    Publish: " + publish);
        mLogger.debug("     Content:\n " + content);
        
        if (validateUser(userid, password)) {
            try {
                Timestamp current = new Timestamp(System.currentTimeMillis());
                
                Weblogger roller = WebloggerFactory.getWeblogger();
                WeblogEntryManager weblogMgr = roller.getWeblogEntryManager();
                WeblogEntry entry = weblogMgr.getWeblogEntry(postid);
                entry.setText(content);
                entry.setUpdateTime(current);
                if (publish) {
                    entry.setStatus(PubStatus.PUBLISHED);
                } else {
                    entry.setStatus(PubStatus.DRAFT);
                }
                
                // save the entry
                weblogMgr.saveWeblogEntry(entry);
                roller.flush();
                
                // notify cache
                flushPageCache(entry.getWebsite());
                
                return true;
            } catch (Exception e) {
                String msg = "ERROR in BlooggerAPIHander.editPost";
                mLogger.error(msg,e);
                throw new XmlRpcException(UNKNOWN_EXCEPTION, msg);
            }
        }
        return false;
    }
