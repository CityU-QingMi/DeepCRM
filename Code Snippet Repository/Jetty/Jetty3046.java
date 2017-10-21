    public void addVirtualHosts(String[] virtualHosts)
    {
        if (virtualHosts == null)  // since this is add, we don't null the old ones
        {
            return;
        }
        else
        {
            List<String> currentVirtualHosts = null;
            if (_vhosts != null)
            {
                currentVirtualHosts = new ArrayList<String>(Arrays.asList(_vhosts));
            }
            else
            {
                currentVirtualHosts = new ArrayList<String>();
            }

            for (int i = 0; i < virtualHosts.length; i++)
            {
                String normVhost = normalizeHostname(virtualHosts[i]);
                if (!currentVirtualHosts.contains(normVhost))
                {
                    currentVirtualHosts.add(normVhost);
                }
            }
            _vhosts = currentVirtualHosts.toArray(new String[0]);
        }
    }
