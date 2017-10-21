    @Override
    public boolean matchesRequirements( Map<String, String> requirements )
    {
        for ( Map.Entry<String, String> requirement : requirements.entrySet() )
        {
            String key = requirement.getKey();

            RequirementMatcher matcher = provides.get( key );

            if ( matcher == null )
            {
                getLog().debug( "Toolchain " + this + " is missing required property: " + key );
                return false;
            }
            if ( !matcher.matches( requirement.getValue() ) )
            {
                getLog().debug( "Toolchain " + this + " doesn't match required property: " + key );
                return false;
            }
        }
        return true;
    }
