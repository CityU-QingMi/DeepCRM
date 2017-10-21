    public String getMavenLocalRepoDir()
    {
        String localRepo = getProperties().getString("maven.local.repo");

        if (Utils.isBlank(localRepo))
            localRepo = System.getenv("JETTY_MAVEN_LOCAL_REPO");

        if (Utils.isBlank(localRepo))
            localRepo = System.getenv("MAVEN_LOCAL_REPO");

        return localRepo;
    }
