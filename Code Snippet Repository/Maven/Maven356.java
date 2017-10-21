    private Dependency createDependency( String gid,
                                         String aid,
                                         String ver )
    {
        Dependency dep = new Dependency();
        dep.setGroupId( gid );
        dep.setArtifactId( aid );
        dep.setVersion( ver );

        return dep;
    }
