    private List<Dependency> getDependencies()
    {
        List<Dependency> dependencies = new ArrayList<>();
        dependencies.add( new Dependency( B, A ) );
        dependencies.add( new Dependency( C, A ) );
        dependencies.add( new Dependency( X, B ) );
        dependencies.add( new Dependency( X, C ) );
        dependencies.add( new Dependency( Y, B ) );
        dependencies.add( new Dependency( Z, C ) );
        return dependencies;
    }
