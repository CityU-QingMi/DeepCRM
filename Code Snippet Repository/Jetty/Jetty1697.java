    private ManagedSelector chooseSelector(SelectableChannel channel)
    {
        // Ideally we would like to have all connections from the same client end
        // up on the same selector (to try to avoid smearing the data from a single
        // client over all cores), but because of proxies, the remote address may not
        // really be the client - so we have to hedge our bets to ensure that all
        // channels don't end up on the one selector for a proxy.
        ManagedSelector candidate1 = null;
        if (channel != null)
        {
            try
            {
                if (channel instanceof SocketChannel)
                {
                    SocketAddress remote = ((SocketChannel)channel).getRemoteAddress();
                    if (remote instanceof InetSocketAddress)
                    {
                        byte[] addr = ((InetSocketAddress)remote).getAddress().getAddress();
                        if (addr != null)
                        {
                            int s = addr[addr.length - 1] & 0xFF;
                            candidate1 = _selectors[s % getSelectorCount()];
                        }
                    }
                }
            }
            catch (IOException x)
            {
                LOG.ignore(x);
            }
        }

        // The ++ increment here is not atomic, but it does not matter,
        // so long as the value changes sometimes, then connections will
        // be distributed over the available selectors.
        long s = _selectorIndex++;
        int index = (int)(s % getSelectorCount());
        ManagedSelector candidate2 = _selectors[index];

        if (candidate1 == null || candidate1.size() >= candidate2.size() * 2)
            return candidate2;
        return candidate1;
    }
