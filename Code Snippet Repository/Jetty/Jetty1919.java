    protected Name stripProtocol (Name name)
        throws NamingException
    {
        if ((name != null) && (name.size() > 0))
        {
            String head = name.get(0);

            if(__log.isDebugEnabled())__log.debug("Head element of name is: "+head);

            if (head.startsWith(URL_PREFIX))
            {
                head = head.substring (URL_PREFIX.length());
                name.remove(0);
                if (head.length() > 0)
                    name.add(0, head);

                if(__log.isDebugEnabled())__log.debug("name modified to "+name.toString());
            }
        }

        return name;
    }
