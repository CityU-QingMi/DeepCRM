    @Override
    public void prevent(ClassLoader loader)
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try 
        {
            factory.newDocumentBuilder();
        } 
        catch (Exception e) 
        {
            LOG.warn(e);
        }

    }
