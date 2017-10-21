    public void init(ServletConfig config) throws ServletException {
        
        super.init(config);
        
        // lookup the authenticator we are going to use and instantiate it
        try {
            String name = WebloggerConfig.getProperty("comment.authenticator.classname");
            
            Class clazz = Class.forName(name);
            this.authenticator = (CommentAuthenticator) clazz.newInstance();
            
        } catch(Exception e) {
            mLogger.error(e);
            this.authenticator = new DefaultCommentAuthenticator();
        }

    }
