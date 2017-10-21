    private void copyModules() throws IOException
    {
        System.out.println("Copying modules ...");
        Path modulesDir = destDir.resolve("modules");
        FS.ensureDirExists(modulesDir.toFile());

        PathMatcher matcher = getPathMatcher("glob:**.mod");
        Renamer renamer = new NoRenamer();
        FileCopier copier = new NormalFileCopier();
        copyDir(srcDir.resolve("modules"),modulesDir,matcher,renamer,copier);
    }
