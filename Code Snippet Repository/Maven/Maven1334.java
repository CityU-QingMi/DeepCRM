    @Override
    public void activate()
    {
        logger.warn( "The SLF4J binding actually used is not supported by Maven: " + slf4jBinding );
        logger.warn( "Maven supported bindings are:" );

        String ls = System.getProperty( "line.separator" );

        for ( Map.Entry<URL, Set<Object>> entry : supported.entrySet() )
        {
            StringBuilder sb = new StringBuilder();
            sb.append( "(from " ).append( entry.getKey().toExternalForm() ).append( ')' );

            for ( Object binding : entry.getValue() )
            {
                sb.append( ls ).append( "- " ).append( binding );
            }

            logger.warn( sb.toString() );
        }
    }
