    public URL getResource(String path) throws MalformedURLException {
        if ("/WEB-INF/web.xml".equals(path)) {
            if (ActionContext.getContext() != null) {
                ServletContext context = ServletActionContext.getServletContext();
                return context.getResource(path);
            }

            return null;
        }
        return classLoaderInterface.getResource(path);
    }
