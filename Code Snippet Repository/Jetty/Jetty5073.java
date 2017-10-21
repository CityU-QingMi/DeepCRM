    public static void appendSchemeHostPort(StringBuilder url,String scheme,String server, int port)
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
