    public void setPolicy(WebSocketPolicy policy)
    {
        for (Extension extension : extensions)
        {
            if (extension instanceof AbstractExtension)
            {
                ((AbstractExtension)extension).setPolicy(policy);
            }
        }
    }
