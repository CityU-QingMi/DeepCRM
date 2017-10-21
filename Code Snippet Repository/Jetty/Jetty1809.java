    private List<String> getUserRoles(DirContext dirContext, String username, Attributes attributes) throws LoginException, NamingException
    {
        String rdnValue = username;
        Attribute attribute = attributes.get(_userRdnAttribute);
        if (attribute != null)
        {
            try
            {
                rdnValue = (String)attribute.get();        // switch to the value stored in the _userRdnAttribute if we can
            }
            catch (NamingException e)
            {
            }
        }

        String userDn = _userRdnAttribute + "=" + rdnValue + "," + _userBaseDn;

        return getUserRolesByDn(dirContext, userDn);
    }
