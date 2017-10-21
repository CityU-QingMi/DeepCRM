    private void saveLines(Path path, List<String> lines) throws IOException
    {
        try (BufferedWriter writer = Files.newBufferedWriter(path,StandardCharsets.UTF_8,StandardOpenOption.TRUNCATE_EXISTING))
        {
            for (String line : lines)
            {
                writer.write(line);
                writer.write(System.lineSeparator());
            }
        }
    }
