    private Map<String, ClassLoader> calcImports( MavenProject project, ClassLoader parent, List<String> imports )
    {
        Map<String, ClassLoader> foreignImports = new HashMap<>();

        ClassLoader projectRealm = project.getClassRealm();
        if ( projectRealm != null )
        {
            foreignImports.put( "", projectRealm );
        }
        else
        {
            foreignImports.put( "", classRealmManager.getMavenApiRealm() );
        }

        if ( parent != null && imports != null )
        {
            for ( String parentImport : imports )
            {
                foreignImports.put( parentImport, parent );
            }
        }

        return foreignImports;
    }
