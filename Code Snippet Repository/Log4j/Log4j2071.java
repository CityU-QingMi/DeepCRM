    static void createJar(final URI jarURI, final File workDir, final File f) throws Exception {
        final Map<String, String> env = new HashMap<>(); 
        env.put("create", "true");
        final URI uri = URI.create("jar:file://" + jarURI.getRawPath());
        try (FileSystem zipfs = FileSystems.newFileSystem(uri, env)) {   
            final Path path = zipfs.getPath(workDir.toPath().relativize(f.toPath()).toString());
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }
            Files.copy(f.toPath(),
                   path, 
                   StandardCopyOption.REPLACE_EXISTING ); 
        } 
    }
