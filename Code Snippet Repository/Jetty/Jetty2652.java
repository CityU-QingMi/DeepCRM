    private Path extractPackedFile( JarFileResource configResource )
        throws IOException
    {
        String uri = configResource.getURI().toASCIIString();
        int colon = uri.lastIndexOf(":");
        int bang_slash = uri.indexOf("!/");
        if (colon<0 || bang_slash<0 || colon>bang_slash)
            throw new IllegalArgumentException("Not resolved JarFile resource: "+uri);        
        String entry_path = uri.substring(colon+2).replace("!/","__").replace('/','_').replace('.','_');

        Path tmpDirectory = Files.createTempDirectory( "users_store" );
        tmpDirectory.toFile().deleteOnExit();
        Path extractedPath = Paths.get(tmpDirectory.toString(), entry_path);
        Files.deleteIfExists( extractedPath );
        extractedPath.toFile().deleteOnExit();
        IO.copy(configResource.getInputStream(),new FileOutputStream(extractedPath.toFile()));
        if (isHotReload())
        {
            LOG.warn("Cannot hot reload from packed configuration: {}",configResource);
            setHotReload(false);
        }
        return extractedPath;
    }
