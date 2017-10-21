    private String getServerIP()
        throws Exception
    {
        Socket s = null;
        try {
            if (getProperty("http.proxyHost") != null)
            {
                s = new Socket(getProperty("http.proxyHost"),
                               parseInt(getProperty("http.proxyPort", "80")));
            } 
            else
            {
                int port = 80;
                
                URL url = new URL(_url);
                if (url.getPort() != -1) {
                    port = url.getPort();
                }
                s = new Socket(url.getHost(), port);
            }
            return s.getLocalAddress().getHostAddress();
        }
        finally
        {
            try
            {
                if (s != null)
                    s.close();
            } 
            catch (IOException ex)
            {
                LOG.ignore(ex);
            }
        }
    }
