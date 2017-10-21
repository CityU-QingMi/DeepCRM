    public List<String> readRequestLines() throws IOException
    {
        LOG.debug("Reading client request header");
        List<String> lines = new ArrayList<>();

        BufferedReader in = new BufferedReader(new InputStreamReader(getInputStream()));
        for (String line = in.readLine(); line != null; line = in.readLine())
        {
            if (line.length() == 0)
            {
                break;
            }
            lines.add(line);
        }

        return lines;
    }
