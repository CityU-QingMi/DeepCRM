    private List<String> readLines(Path path) throws IOException
    {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(path,StandardCharsets.UTF_8))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                lines.add(line);
            }
        }

        return lines;
    }
