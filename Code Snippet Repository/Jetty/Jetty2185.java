    public URL getFileURL(URL url) throws Exception
 
    {
        if ("bundleresource".equals(url.getProtocol()) || "bundleentry".equals(url.getProtocol()))
        {

            URLConnection conn = url.openConnection();
            conn.setDefaultUseCaches(Resource.getDefaultUseCaches());
            if (BUNDLE_URL_CONNECTION_getFileURL == null 
                && 
                match (conn.getClass().getName(), BUNDLE_URL_CONNECTION_CLASSES))
            {
                BUNDLE_URL_CONNECTION_getFileURL = conn.getClass().getMethod("getFileURL", null);
                BUNDLE_URL_CONNECTION_getFileURL.setAccessible(true);
            }
            if (BUNDLE_URL_CONNECTION_getFileURL != null) { return (URL) BUNDLE_URL_CONNECTION_getFileURL.invoke(conn, null); }

        }
        return url;
    }
