    public void selectProjectRealm( MavenProject project )
    {
        ClassLoader projectRealm = project.getClassRealm();

        if ( projectRealm == null )
        {
            projectRealm = classRealmManager.getCoreRealm();
        }

        Thread.currentThread().setContextClassLoader( projectRealm );
    }
