    public void removeVirtualHosts(String[] virtualHosts)
    {
        if (virtualHosts == null)
        {
            return; // do nothing
        }
        else if ( _vhosts == null || _vhosts.length == 0)
        {
            return; // do nothing
        }
        else
        {
            List<String> existingVirtualHosts = new ArrayList<String>(Arrays.asList(_vhosts));

            for (int i = 0; i < virtualHosts.length; i++)
            {
                String toRemoveVirtualHost = normalizeHostname(virtualHosts[i]);
                if (existingVirtualHosts.contains(toRemoveVirtualHost))
                {
                    existingVirtualHosts.remove(toRemoveVirtualHost);
                }
            }

            if (existingVirtualHosts.isEmpty())
            {
                _vhosts = null; // if we ended up removing them all, just null out _vhosts
            }
            else
            {
                _vhosts = existingVirtualHosts.toArray(new String[0]);
            }
        }
    }
