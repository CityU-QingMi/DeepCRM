    public boolean setTemplate(String appkey, String blogid, String userid,
            String password, String templateData,
            String templateType) throws Exception {
        
        mLogger.debug("setTemplate() Called =====[ SUPPORTED ]=====");
        mLogger.debug("     Appkey: " + appkey);
        mLogger.debug("     BlogId: " + blogid);
        mLogger.debug("     UserId: " + userid);
        mLogger.debug("   Template: " + templateData);
        mLogger.debug("       Type: " + templateType);
        
        validate(blogid, userid, password);
        
        if (!templateType.equals("main")) {
            throw new XmlRpcException(
                    UNKNOWN_EXCEPTION, "Roller supports only main template");
        }
        
        try {
            WeblogTemplate template = WebloggerFactory.getWeblogger().getWeblogManager().getTemplate(templateType);
            CustomTemplateRendition ctr = template.getTemplateRendition(RenditionType.STANDARD);
            if (ctr != null) {
                ctr.setTemplate(templateData);
                WebloggerFactory.getWeblogger().getWeblogManager().saveTemplateRendition(ctr);
                flushPageCache(template.getWeblog());
                return true;
            } else {
                mLogger.error("Cannot find standard rendering for template.");
                return false;
            }
        } catch (WebloggerException e) {
            String msg = "ERROR in BloggerAPIHander.setTemplate";
            mLogger.error(msg,e);
            throw new XmlRpcException(UNKNOWN_EXCEPTION,msg);
        }
    }
