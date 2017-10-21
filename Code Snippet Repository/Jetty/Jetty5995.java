    @Override
    public void deconfigure (WebAppContext context) throws Exception
    {      
        context.setWelcomeFiles(null);

        if (context.getErrorHandler() instanceof ErrorPageErrorHandler)
            ((ErrorPageErrorHandler) 
                    context.getErrorHandler()).setErrorPages(null);

        // TODO remove classpaths from classloader
    }
