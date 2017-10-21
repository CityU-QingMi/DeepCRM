    @Override
    public int hashCode()
    {
        if ( md == null )
        {
            return super.hashCode();
        }
        StringBuilder hashString = new StringBuilder( 128 );
        hashString.append( md.groupId ).append( '|' );
        hashString.append( md.artifactId ).append( '|' );

        if ( compareVersion )
        {
            hashString.append( md.version ).append( '|' );
        }

        if ( compareScope )
        {
            hashString.append( md.getArtifactScope() ).append( '|' );
        }

        return hashString.toString().hashCode();

        // BASE64Encoder b64 = new BASE64Encoder();
        // return b64.encode( hashString.toString().getBytes() ).hashCode();
    }
