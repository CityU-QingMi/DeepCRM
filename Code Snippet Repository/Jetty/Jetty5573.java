    private static void updateFile(Path path, String newContents) throws IOException
    {
        try (FileOutputStream out = new FileOutputStream(path.toFile()))
        {
            out.write(newContents.getBytes(StandardCharsets.UTF_8));
            out.flush();
            out.getChannel().force(true);
            out.getFD().sync();
        }
    }
