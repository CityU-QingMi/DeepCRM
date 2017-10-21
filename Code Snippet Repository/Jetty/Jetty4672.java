    private List<String> lines(File file) throws IOException
    {
        if (!file.exists() || !file.canRead())
            return Collections.emptyList();
        List<String> ret = new ArrayList<>();
        try (FileReader reader = new FileReader(file);
             BufferedReader buf = new BufferedReader(reader))
        {
            String line;
            while ((line = buf.readLine()) != null)
            {
                line = line.trim();
                if (line.length() > 0)
                {
                    ret.add(line);
                }
            }
        }
        return ret;
    }
