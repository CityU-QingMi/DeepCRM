    public JettyDistro(TestingDir testdir, String artifact) throws IOException
    {
        this.jettyHomeDir = testdir.getPath().toFile();
        if (artifact != null)
        {
            this.artifactName = artifact;
        }

        copyBaseDistro();
    }
