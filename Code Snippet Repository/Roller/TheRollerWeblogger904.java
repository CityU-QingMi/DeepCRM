    public Object getUsersBlogs(String appkey, String userid, String password)
    throws Exception {
        
        mLogger.debug("getUsersBlogs() Called ===[ SUPPORTED ]=======");
        mLogger.debug("     Appkey: " + appkey);
        mLogger.debug("     UserId: " + userid);
        
        Vector result = new Vector();
        if (validateUser(userid, password)) {
            try {
                UserManager umgr = WebloggerFactory.getWeblogger().getUserManager();
                User user = umgr.getUserByUserName(userid);
                
                // get list of user's enabled websites
                List<Weblog> websites = WebloggerFactory.getWeblogger().getWeblogManager().getUserWeblogs(user, true);
                for (Weblog website : websites) {
                    // only include weblog's that have client API support enabled
                    if (Boolean.TRUE.equals(website.getEnableBloggerApi())) {
                        Hashtable blog = new Hashtable(3);
                        blog.put("url", website.getURL());
                        blog.put("blogid", website.getHandle());
                        blog.put("blogName", website.getName());
                        result.add(blog);
                    }
                }
            } catch (Exception e) {
                String msg = "ERROR in BlooggerAPIHander.getUsersBlogs";
                mLogger.error(msg,e);
                throw new XmlRpcException(UNKNOWN_EXCEPTION, msg);
            }
        }
        return result;
    }
