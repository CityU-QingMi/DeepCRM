    private static void mergeExtensionLists( Build childBuild, Build parentBuild )
    {
        for ( Extension e : parentBuild.getExtensions() )
        {
            if ( !childBuild.getExtensions().contains( e ) )
            {
                childBuild.addExtension( e );
            }
        }
    }
