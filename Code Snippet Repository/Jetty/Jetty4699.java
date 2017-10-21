    private void copyLibs() throws IOException
    {
        System.out.println("Copying libs (lib dir) ...");
        Path libsDir = destDir.resolve("lib");
        FS.ensureDirExists(libsDir.toFile());

        PathMatcher matcher = getPathMatcher("glob:**.jar");
        Renamer renamer = new RegexRenamer("-9\\.[0-9.]*(v[0-9-]*)?(-SNAPSHOT)?(RC[0-9])?(M[0-9])?","-" + JETTY_VERSION);
        FileCopier copier = new TouchFileCopier();
        copyDir(srcDir.resolve("lib"),libsDir,matcher,renamer,copier);
    }
