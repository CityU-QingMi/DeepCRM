    @Override
    public Authentication findAuthentication(String type, URI uri, String realm)
    {
        for (Authentication authentication : authentications)
        {
            if (authentication.matches(type, uri, realm))
                return authentication;
        }
        return null;
    }
