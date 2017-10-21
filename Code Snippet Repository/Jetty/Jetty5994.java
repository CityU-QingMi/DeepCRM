    protected Resource findWebXml(WebAppContext context) throws IOException, MalformedURLException
    {
        String descriptor = context.getDescriptor();
        if (descriptor != null)
        {
            Resource web = context.newResource(descriptor);
            if (web.exists() && !web.isDirectory()) return web;
        }

        Resource web_inf = context.getWebInf();
        if (web_inf != null && web_inf.isDirectory())
        {
            // do web.xml file
            Resource web = web_inf.addPath("web.xml");
            if (web.exists()) return web;
            if (LOG.isDebugEnabled())
                LOG.debug("No WEB-INF/web.xml in " + context.getWar() + ". Serving files and default/dynamic servlets only");
        }
        return null;
    }
