    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "type:" ).append( getType() );
        builder.append( '{' );

        Iterator<Map.Entry<String, RequirementMatcher>> providesIter = provides.entrySet().iterator();
        while ( providesIter.hasNext() )
        {
            Map.Entry<String, RequirementMatcher> provideEntry = providesIter.next();
            builder.append( provideEntry.getKey() ).append( " = " ).append( provideEntry.getValue() );
            if ( providesIter.hasNext() )
            {
                builder.append( ';' );
            }
        }
        
        builder.append( '}' );
        
        return builder.toString();
    }
