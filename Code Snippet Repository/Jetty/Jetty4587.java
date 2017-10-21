    private void parseIni() throws IOException
    {
        try (BufferedReader reader = Files.newBufferedReader(startIni,StandardCharsets.UTF_8))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                line = line.trim();
                if (line.startsWith("--module="))
                {
                    List<String> moduleNames = Props.getValues(line);
                    this.modulesPresent.addAll(moduleNames);
                }
                else if (!line.startsWith("-") && line.contains("="))
                {
                    String key = line.substring(0,line.indexOf('='));
                    this.propsPresent.add(key);
                }
            }
        }
    }
