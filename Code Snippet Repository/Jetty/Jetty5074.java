    public static void appendSchemeHostPort(StringBuffer url,String scheme,String server, int port)
    {
        synchronized (url)
        {
            url.append(scheme).append("://").append(HostPort.normalizeHost(server));

            if (port > 0)
            {
                switch(scheme)
                {
                    case "http":
                        if (port!=80) 
                            url.append(':').append(port);
                        break;
                        
                    case "https":
                        if (port!=443) 
                            url.append(':').append(port);
                        break;

                    default:
                        url.append(':').append(port);
                }
            }
        }
    }
