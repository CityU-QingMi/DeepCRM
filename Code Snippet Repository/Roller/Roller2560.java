    public String getTemplate(String appkey, String blogid, String userid,
            String password, String templateType)
            throws Exception {
        
        mLogger.debug("getTemplate() Called =====[ SUPPORTED ]=====");
        mLogger.debug("     Appkey: " + appkey);
        mLogger.debug("     BlogId: " + blogid);
        mLogger.debug("     UserId: " + userid);
        mLogger.debug("       Type: " + templateType);
        
        validate(blogid, userid,password);
        
        try {
            CustomTemplateRendition ctr = null;
            WeblogTemplate template = WebloggerFactory.getWeblogger().getWeblogManager().getTemplate(templateType);
            if (template != null) {
                ctr = template.getTemplateRendition(RenditionType.STANDARD);
            }
            if (null == ctr) {
                throw new XmlRpcException(UNKNOWN_EXCEPTION,"Standard rendering of template not found");
            } else {
                return ctr.getTemplate();
            }
        } catch (Exception e) {
            String msg = "ERROR in BlooggerAPIHander.getTemplate";
            mLogger.error(msg,e);
            throw new XmlRpcException(UNKNOWN_EXCEPTION,msg);
        }
    }
