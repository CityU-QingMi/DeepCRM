    String getRepositoryKey( ArtifactRepository repository )
    {
        StringBuilder buffer = new StringBuilder( 256 );

        Proxy proxy = repository.getProxy();
        if ( proxy != null )
        {
            if ( proxy.getUserName() != null )
            {
                int hash = ( proxy.getUserName() + proxy.getPassword() ).hashCode();
                buffer.append( hash ).append( '@' );
            }
            buffer.append( proxy.getHost() ).append( ':' ).append( proxy.getPort() ).append( '>' );
        }

        // consider the username&password because a repo manager might block artifacts depending on authorization
        Authentication auth = repository.getAuthentication();
        if ( auth != null )
        {
            int hash = ( auth.getUsername() + auth.getPassword() ).hashCode();
            buffer.append( hash ).append( '@' );
        }

        // consider the URL (instead of the id) as this most closely relates to the contents in the repo
        buffer.append( repository.getUrl() );

        return buffer.toString();
    }
