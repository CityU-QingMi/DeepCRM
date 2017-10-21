    public static Credential getCredential(String credential)
    {
        if (credential.startsWith(Crypt.__TYPE))
            return new Crypt(credential);
        if (credential.startsWith(MD5.__TYPE))
            return new MD5(credential);

        for (CredentialProvider cp : CREDENTIAL_PROVIDER_LOADER)
        {
            if (credential.startsWith(cp.getPrefix()))
            {
                final Credential credentialObj = cp.getCredential(credential);
                if (credentialObj != null)
                {
                    return credentialObj;
                }
            }
        }

        return new Password(credential);
    }
