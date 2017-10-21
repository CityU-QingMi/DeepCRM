    public void setVirtualHosts(String[] vhosts)
    {
        if (vhosts == null)
        {
            _vhosts = vhosts;
        }
        else
        {
            _vhosts = new String[vhosts.length];
            for (int i = 0; i < vhosts.length; i++)
                _vhosts[i] = normalizeHostname(vhosts[i]);
        }
    }
