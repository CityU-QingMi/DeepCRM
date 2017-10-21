    private static Authentication toAuthentication( org.apache.maven.artifact.repository.Authentication auth )
    {
        Authentication result = null;
        if ( auth != null )
        {
            AuthenticationBuilder authBuilder = new AuthenticationBuilder();
            authBuilder.addUsername( auth.getUsername() ).addPassword( auth.getPassword() );
            authBuilder.addPrivateKey( auth.getPrivateKey(), auth.getPassphrase() );
            result = authBuilder.build();
        }
        return result;
    }
