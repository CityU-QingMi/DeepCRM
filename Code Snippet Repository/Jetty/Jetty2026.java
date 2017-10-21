    private void sendData(Properties data)
        throws Exception
    {
        data.put("account", _uuid);
        data.put("appserver", "Jetty");
        data.put("localIp", _srvip);
        if (_appid == null)
            data.put("lowestPort", getHttpPort());
        else
            data.put("lowestPort", _appid);
        if (_session != null)
            data.put("session", _session);
        
        Properties response = sendRequest(data);
        
        parseResponse(response);
    }
