    public static void makeFile(Path dir, String relFilePath, String... contents) throws IOException
    {
        Path outputFile = dir.resolve(OS.separators(relFilePath));
        FS.ensureDirExists(outputFile.getParent());
        try (BufferedWriter writer = Files.newBufferedWriter(outputFile);
             PrintWriter out = new PrintWriter(writer))
        {
            for (String content : contents)
            {
                out.println(content);
            }
        }
    }
