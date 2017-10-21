    @Override
    public String getLocalAddr()
    {
        if (_channel==null)
        {
            try
            {
                String name =InetAddress.getLocalHost().getHostAddress();
                if (StringUtil.ALL_INTERFACES.equals(name))
                    return null;
                return name;
            }
            catch (java.net.UnknownHostException e)
            {
                LOG.ignore(e);
            }
        }

        InetSocketAddress local=_channel.getLocalAddress();
        if (local==null)
            return "";
        InetAddress address = local.getAddress();
        if (address==null)
            return local.getHostString();
        return address.getHostAddress();
    }
