    public NegotiatingServerConnectionFactory(String protocol, String... negotiatedProtocols)
    {
        super(protocol);
        this.negotiatedProtocols = new ArrayList<>();
        if (negotiatedProtocols != null)
        {
            // Trim the values, as they may come from XML configuration.
            for (String p : negotiatedProtocols)
            {
                p = p.trim();
                if (!p.isEmpty())
                    this.negotiatedProtocols.add(p.trim());
            }
        }
    }
