    public TextFile(Path file) throws FileNotFoundException, IOException
    {
        this.file = file;
        init();
        
        if (!FS.canReadFile(file))
        {
            StartLog.debug("Skipping read of missing file: %s",file.toAbsolutePath());
            return;
        }

        try (BufferedReader buf = Files.newBufferedReader(file,StandardCharsets.UTF_8))
        {
            String line;
            while ((line = buf.readLine()) != null)
            {
                if (line.length() == 0)
                {
                    continue;
                }

                allLines.add(line);
                
                if (line.charAt(0) == '#')
                {
                    continue;
                }

                // TODO - bad form calling derived method from base class constructor
                process(line.trim());
            }
        }
    }
