    public URL getLocalURL(URL url)
    throws Exception
    {
        if ("bundleresource".equals(url.getProtocol()) || "bundleentry".equals(url.getProtocol()))
        {

            URLConnection conn = url.openConnection();
            conn.setDefaultUseCaches(Resource.getDefaultUseCaches());
            if (BUNDLE_URL_CONNECTION_getLocalURL == null && match(conn.getClass().getName(), BUNDLE_URL_CONNECTION_CLASSES))
            {
                BUNDLE_URL_CONNECTION_getLocalURL = conn.getClass().getMethod("getLocalURL", null);
                BUNDLE_URL_CONNECTION_getLocalURL.setAccessible(true);
            }
            if (BUNDLE_URL_CONNECTION_getLocalURL != null) { return (URL) BUNDLE_URL_CONNECTION_getLocalURL.invoke(conn, null); }
        }
        return url;
    }
