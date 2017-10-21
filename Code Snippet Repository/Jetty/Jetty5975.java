    private void dumpUrl()
    {
        Connector[] connectors = getServer().getConnectors();
        for (int i=0;i<connectors.length;i++)
        {
            String displayName = getDisplayName();
            if (displayName == null)
                displayName = "WebApp@"+connectors.hashCode();

            LOG.info(displayName + " at http://" + connectors[i].toString() + getContextPath());
        }
    }
