    public static int getScope(String scope){
        int ret = PageContext.PAGE_SCOPE;
        
        if("request".equalsIgnoreCase(scope)){
            ret = PageContext.REQUEST_SCOPE;
        }else if("session".equalsIgnoreCase(scope)){
            ret = PageContext.SESSION_SCOPE;
        }else if("application".equalsIgnoreCase(scope)){
            ret = PageContext.APPLICATION_SCOPE;
        }
        
        return ret;
    }
