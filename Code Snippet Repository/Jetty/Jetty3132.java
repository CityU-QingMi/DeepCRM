    @Override
    public String getObjectContextBasis()
    {
        StringBuilder buffer = new StringBuilder();
        for (ConnectionFactory f:_connector.getConnectionFactories())
        {
            String protocol=f.getProtocol();
            if (protocol!=null)
            {
                if (buffer.length()>0)
                    buffer.append("|");
                buffer.append(protocol);
            }
        }
        
        return String.format("%s@%x",buffer.toString(),_connector.hashCode());
    }
