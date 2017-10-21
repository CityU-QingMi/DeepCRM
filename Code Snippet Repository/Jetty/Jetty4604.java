    private Path getLocalRepoFile(Coordinates coords) throws IOException
    {
        Path localFile = localRepositoryDir.resolve(coords.toPath());
        if (FS.canReadFile(localFile))
            return localFile;

        // Download, if needed
        if (!readonly)
        {
            download(coords.toCentralURI(),localFile);
            return localFile;
        }
        
        return null;
    }
