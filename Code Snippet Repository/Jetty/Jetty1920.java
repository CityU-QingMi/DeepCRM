    protected String stripProtocol (String name)
    {
        String newName = name;

        if ((name != null) && (!name.equals("")))
        {
            if (name.startsWith(URL_PREFIX))
               newName = name.substring(URL_PREFIX.length());
        }

        return newName;
    }
