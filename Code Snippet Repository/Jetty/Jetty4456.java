    public BaseBuilder(BaseHome baseHome, StartArgs args)
    {
        this.baseHome = baseHome;
        this.startArgs = args;
        this.fileInitializers = new ArrayList<>();

        // Establish FileInitializers
        if (args.isTestingModeEnabled())
        {
            // Copy from basehome
            fileInitializers.add(new BaseHomeFileInitializer(baseHome));

            // Handle local directories
            fileInitializers.add(new LocalFileInitializer(baseHome));
            
            // No downloads performed
            fileInitializers.add(new TestFileInitializer(baseHome));
        }
        else if (args.isCreateFiles())
        {
            // Handle local directories
            fileInitializers.add(new LocalFileInitializer(baseHome));
            
            // Downloads are allowed to be performed
            // Setup Maven Local Repo
            Path localRepoDir = args.findMavenLocalRepoDir();
            if (localRepoDir != null)
            {
                // Use provided local repo directory
                fileInitializers.add(new MavenLocalRepoFileInitializer(baseHome,localRepoDir,args.getMavenLocalRepoDir()==null));
            }
            else
            {
                // No no local repo directory (direct downloads)
                fileInitializers.add(new MavenLocalRepoFileInitializer(baseHome));
            }

            // Copy from basehome
            fileInitializers.add(new BaseHomeFileInitializer(baseHome));
            
            // Normal URL downloads
            fileInitializers.add(new UriFileInitializer(baseHome));
        }
    }
