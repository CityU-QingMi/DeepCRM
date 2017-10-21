    public String toString()
    {
        StringBuilder sb = new StringBuilder()
                .append( "REQUEST: " ).append(  "\n" )
                .append( "artifact: " ).append( mad ).append(  "\n" )
                .append( artifactDependencies ).append(  "\n" )
                .append( "localRepository: " ).append(  localRepository ).append(  "\n" )
                .append( "remoteRepositories: " ).append(  remoteRepositories ).append(  "\n" )
                ;

        return sb.toString();
    }
