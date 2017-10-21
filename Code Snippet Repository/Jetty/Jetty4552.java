    public void expandLibs() throws IOException
    {
        StartLog.debug("Expanding Libs");
        for (String rawlibref : rawLibs)
        {
            StartLog.debug("rawlibref = " + rawlibref);
            String libref = properties.expand(rawlibref);
            StartLog.debug("expanded = " + libref);

            // perform path escaping (needed by windows)
            libref = libref.replaceAll("\\\\([^\\\\])","\\\\\\\\$1");

            for (Path libpath : baseHome.getPaths(libref))
            {
                classpath.addComponent(libpath.toFile());
            }
        }
    }
