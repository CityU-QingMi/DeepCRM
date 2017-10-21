    private String getUserCredentials(Attributes attributes) throws LoginException
    {
        String ldapCredential = null;

        Attribute attribute = attributes.get(_userPasswordAttribute);
        if (attribute != null)
        {
            try
            {
                byte[] value = (byte[])attribute.get();

                ldapCredential = new String(value);
            }
            catch (NamingException e)
            {
                LOG.debug("no password available under attribute: " + _userPasswordAttribute);
            }
        }

        LOG.debug("user cred is: " + ldapCredential);

        return ldapCredential;
    }
