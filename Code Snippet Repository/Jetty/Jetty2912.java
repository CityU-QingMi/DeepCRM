    @Override
    public String getLocalName()
    {
        if (_channel!=null)
        {
            InetSocketAddress local=_channel.getLocalAddress();
            if (local!=null)
                return local.getHostString();
        }

        try
        {
            String name =InetAddress.getLocalHost().getHostName();
            if (StringUtil.ALL_INTERFACES.equals(name))
                return null;
            return name;
        }
        catch (java.net.UnknownHostException e)
        {
            LOG.ignore(e);
        }
        return null;
    }
