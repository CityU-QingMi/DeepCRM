    private Attributes getUserAttributes(String username) throws LoginException
    {
        Attributes attributes = null;

        SearchResult result;
        try
        {
            result = findUser(username);
            attributes = result.getAttributes();
        }
        catch (NamingException e)
        {
            throw new LoginException("Root context binding failure.");
        }

        return attributes;
    }
