    @Override
    public String toString()
    {
        StringBuilder buffer = new StringBuilder( 64 );
        buffer.append( "{enabled=" );
        buffer.append( enabled );
        buffer.append( ", checksums=" );
        buffer.append( checksumPolicy );
        buffer.append( ", updates=" );
        buffer.append( updatePolicy );
        buffer.append( '}' );
        return buffer.toString();
    }
