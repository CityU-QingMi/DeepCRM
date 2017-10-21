    public UserInfo getUserInfo(String username) throws Exception
    {
        Attributes attributes = getUserAttributes(username);
        String pwdCredential = getUserCredentials(attributes);

        if (pwdCredential == null)
        {
            return null;
        }

        pwdCredential = convertCredentialLdapToJetty(pwdCredential);
        Credential credential = Credential.getCredential(pwdCredential);
        return new LDAPUserInfo(username, credential, attributes);
    }
