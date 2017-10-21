    @Override
    public List<String> getSubProtocols()
    {
        if (subprotocols == null)
        {
            Enumeration<String> requestProtocols = request.getHeaders("Sec-WebSocket-Protocol");
            if (requestProtocols != null)
            {
                subprotocols = new ArrayList<>(2);
                while (requestProtocols.hasMoreElements())
                {
                    String candidate = requestProtocols.nextElement();
                    Collections.addAll(subprotocols,parseProtocols(candidate));
                }
            }
        }
        return subprotocols;
    }
