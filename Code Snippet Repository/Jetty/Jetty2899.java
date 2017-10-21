    @Override
    public Connection newConnection(Connector connector, EndPoint endp)
    {
        String next=_next;
        if (next==null)
        {
            for (Iterator<String> i = connector.getProtocols().iterator();i.hasNext();)
            {
                String p=i.next();
                if (getProtocol().equalsIgnoreCase(p))
                {
                    next=i.next();
                    break;
                }
            }
        }

        return new ProxyProtocolV1orV2Connection(endp,connector,next);
    }
