    protected void initMultiPart () throws Exception
    {
        //if this servlet can handle multipart requests, ensure tmp files will be
        //cleaned up correctly
        if (((Registration)getRegistration()).getMultipartConfig() != null)
        {
            //Register a listener to delete tmp files that are created as a result of this
            //servlet calling Request.getPart() or Request.getParts()

            ContextHandler ch = ContextHandler.getContextHandler(getServletHandler().getServletContext());
            ch.addEventListener(MultiPartCleanerListener.INSTANCE);
        }
    }
