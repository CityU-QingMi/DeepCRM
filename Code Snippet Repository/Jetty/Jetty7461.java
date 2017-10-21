    public JettyDistro(Class<?> clazz, String artifact) throws IOException
    {
        this.jettyHomeDir = MavenTestingUtils.getTargetTestingDir(clazz,"jettyHome");
        if (artifact != null)
        {
            this.artifactName = artifact;
        }

        copyBaseDistro();
    }
