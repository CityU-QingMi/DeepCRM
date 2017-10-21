    public Object getUserInfo(String appkey, String userid, String password)
    throws Exception {
        
        mLogger.debug("getUserInfo() Called =====[ SUPPORTED ]=====");
        mLogger.debug("     Appkey: " + appkey);
        mLogger.debug("     UserId: " + userid);
        
        validateUser(userid, password);
        
        try {
            Weblogger roller = WebloggerFactory.getWeblogger();
            UserManager userMgr = roller.getUserManager();
            User user = userMgr.getUserByUserName(userid);
            
            // parses full name into two strings, firstname and lastname
            String firstname = "", lastname = "";
            StringTokenizer toker = new StringTokenizer(user.getFullName());
            
            if (toker.hasMoreTokens()) {
                firstname = toker.nextToken();
            }
            
            while (toker.hasMoreTokens()) {
                if ( !lastname.equals("") ) {
                    lastname += " ";
                }
                lastname += toker.nextToken();
            }

            // TODO: Should screen name be renamed nickname and used here?
            // populates user information to return as a result
            Hashtable result = new Hashtable();
            result.put("nickname", user.getUserName());
            result.put("userid", user.getUserName());
            result.put("email", "");
            result.put("lastname", lastname);
            result.put("firstname", firstname);
            
            return result;
        } catch (WebloggerException e) {
            String msg = "ERROR in BlooggerAPIHander.getInfo";
            mLogger.error(msg,e);
            throw new XmlRpcException(UNKNOWN_EXCEPTION,msg);
        }
    }
