    @Override
    public String getRemoteAddr()
    {
        InetSocketAddress remote=_remote;
        if (remote==null)
            remote=_channel.getRemoteAddress();

        if (remote==null)
            return "";

        InetAddress address = remote.getAddress();
        if (address==null)
            return remote.getHostString();

        return address.getHostAddress();
    }
