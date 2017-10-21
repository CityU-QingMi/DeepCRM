    public boolean checkVirtualHost(final Request baseRequest)
    {
        if (_vhosts != null && _vhosts.length > 0)
        {
            String vhost = normalizeHostname(baseRequest.getServerName());

            boolean match = false;
            boolean connectorName = false;
            boolean connectorMatch = false;

            for (String contextVhost:_vhosts)
            {
                if (contextVhost == null || contextVhost.length()==0)
                    continue;
                char c=contextVhost.charAt(0);
                switch (c)
                {
                    case '*':
                        if (contextVhost.startsWith("*."))
                            // wildcard only at the beginning, and only for one additional subdomain level
                            match = match || contextVhost.regionMatches(true,2,vhost,vhost.indexOf(".") + 1,contextVhost.length() - 2);
                        break;
                    case '@':
                        connectorName=true;
                        String name=baseRequest.getHttpChannel().getConnector().getName();
                        boolean m=name!=null && contextVhost.length()==name.length()+1 && contextVhost.endsWith(name);
                        match = match || m;
                        connectorMatch = connectorMatch || m;
                        break;
                    default:
                        match = match || contextVhost.equalsIgnoreCase(vhost);
                }

            }
            if (!match || connectorName && !connectorMatch)
                return false;
        }
        return true;
    }
