    public StringBuilder generateUpgradeRequest()
    {
        StringBuilder req = new StringBuilder();
        req.append("GET ").append(getRequestPath()).append(" HTTP/1.1\r\n");
        req.append("Host: ").append(getRequestHost()).append("\r\n");
        req.append("Upgrade: websocket\r\n");
        req.append("User-Agent: BlockheadClient/JettyTests\r\n");
        req.append("Connection: ").append(connectionValue).append("\r\n");
        for (String header : headers)
        {
            req.append(header);
        }
        req.append("Sec-WebSocket-Key: ").append(getRequestWebSocketKey()).append("\r\n");
        req.append("Sec-WebSocket-Origin: ").append(getRequestWebSocketOrigin()).append("\r\n");
        if (StringUtil.isNotBlank(protocols))
        {
            req.append("Sec-WebSocket-Protocol: ").append(protocols).append("\r\n");
        }

        for (String xtension : extensions)
        {
            req.append("Sec-WebSocket-Extensions: ").append(xtension).append("\r\n");
        }
        req.append("Sec-WebSocket-Version: ").append(version).append("\r\n");
        req.append("\r\n");
        return req;
    }
