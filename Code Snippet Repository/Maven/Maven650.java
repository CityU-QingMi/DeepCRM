    public CoreExports( ClassRealm realm, Set<String> exportedArtifacts, Set<String> exportedPackages )
    {
        Map<String, ClassLoader> packages = new LinkedHashMap<>();
        for ( String pkg : exportedPackages )
        {
            packages.put( pkg, realm );
        }
        this.artifacts = ImmutableSet.copyOf( exportedArtifacts );
        this.packages = ImmutableMap.copyOf( packages );
    }
