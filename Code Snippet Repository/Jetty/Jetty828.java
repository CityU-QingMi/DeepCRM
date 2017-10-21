    public void load() throws Exception
    {
        XmlConfiguration last = null;
        Object[] obj = new Object[this._xmlConfigurations.size()];

        // Configure everything
        for (int i = 0; i < this._xmlConfigurations.size(); i++)
        {
            URL configURL = this._xmlConfigurations.get(i);
            XmlConfiguration configuration = new XmlConfiguration(configURL);
            if (last != null)
                configuration.getIdMap().putAll(last.getIdMap());
            configuration.getProperties().putAll(_properties);
            obj[i] = configuration.configure();
            last = configuration;
        }

        // Test for Server Instance.
        Server foundServer = null;
        int serverCount = 0;
        for (int i = 0; i < this._xmlConfigurations.size(); i++)
        {
            if (obj[i] instanceof Server)
            {
                if (obj[i].equals(foundServer))
                {
                    // Identical server instance found
                    break;
                }
                foundServer = (Server)obj[i];
                serverCount++;
            }
        }

        if (serverCount <= 0)
        {
            throw new Exception("Load failed to configure a " + Server.class.getName());
        }

        Assert.assertEquals("Server load count",1,serverCount);

        this._server = foundServer;
        this._server.setStopTimeout(10);
    }
