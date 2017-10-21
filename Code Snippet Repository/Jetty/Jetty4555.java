    public Path findMavenLocalRepoDir()
    {
        // Try property first
        String localRepo = getMavenLocalRepoDir();

        if (Utils.isBlank(localRepo))
        {
            // Try generic env variable
            String home = System.getenv("HOME");
            Path home_m2_repository = new File(new File(home,".m2"),"repository").toPath();
            if (Files.exists(home_m2_repository))
                localRepo = home_m2_repository.toString();
        }

        // TODO: possibly use Eclipse Aether to manage it ?
        // TODO: see https://bugs.eclipse.org/bugs/show_bug.cgi?id=449511

        // Still blank? then its not specified
        if (Utils.isBlank(localRepo))
        {
            return null;
        }

        Path localRepoDir = new File(localRepo).toPath();
        localRepoDir = localRepoDir.normalize().toAbsolutePath();
        if (Files.exists(localRepoDir) && Files.isDirectory(localRepoDir))
        {
            return localRepoDir;
        }

        StartLog.warn("Not a valid maven local repository directory: %s",localRepoDir);

        // Not a valid repository directory, skip it
        return null;
    }
