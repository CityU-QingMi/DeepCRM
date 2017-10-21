    public Resource getWebInf() throws IOException
    {
        if (super.getBaseResource() == null)
            return null;

        // Iw there a WEB-INF directory?
        Resource web_inf= super.getBaseResource().addPath("WEB-INF/");
        if (!web_inf.exists() || !web_inf.isDirectory())
            return null;

        return web_inf;
    }
