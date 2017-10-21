    private void copyXmls() throws IOException
    {
        System.out.println("Copying xmls (etc dir) ...");
        Path xmlDir = destDir.resolve("etc");
        FS.ensureDirExists(xmlDir.toFile());

        PathMatcher matcher = getPathMatcher("glob:**.xml");
        Renamer renamer = new NoRenamer();
        FileCopier copier = new TouchFileCopier();
        copyDir(srcDir.resolve("etc"),xmlDir,matcher,renamer,copier);
    }
