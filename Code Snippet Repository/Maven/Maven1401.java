    private String getChildPathAdjustment( Model child, Model parent, String childDirectory )
    {
        String adjustment = "";

        if ( parent != null )
        {
            String childName = child.getArtifactId();

/**/
/**/
/**/
/**/
/**/
/**/
/**/
            if ( child.getProjectDirectory() != null )
            {
                childName = child.getProjectDirectory().getName();
            }

            for ( String module : parent.getModules() )
            {
                module = module.replace( '\\', '/' );

                if ( module.regionMatches( true, module.length() - 4, ".xml", 0, 4 ) )
                {
                    module = module.substring( 0, module.lastIndexOf( '/' ) + 1 );
                }

                String moduleName = module;
                if ( moduleName.endsWith( "/" ) )
                {
                    moduleName = moduleName.substring( 0, moduleName.length() - 1 );
                }

                int lastSlash = moduleName.lastIndexOf( '/' );

                moduleName = moduleName.substring( lastSlash + 1 );

                if ( ( moduleName.equals( childName ) || ( moduleName.equals( childDirectory ) ) ) && lastSlash >= 0 )
                {
                    adjustment = module.substring( 0, lastSlash );
                    break;
                }
            }
        }

        return adjustment;
    }
