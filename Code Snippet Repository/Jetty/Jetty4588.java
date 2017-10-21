    @Override
    public String addModule(Module module, Props props) throws IOException
    {
        if (modulesPresent.contains(module.getName()))
        {
            StartLog.info("%-15s already initialised in %s",module.getName(),baseHome.toShortForm(startIni));
            // skip, already present
            return null;
        }

        if (module.isDynamic())
        {
            if (module.hasIniTemplate())
            {
                // warn
                StartLog.warn("%-15s not adding [ini-template] from dynamic module",module.getName());
            }
            return null;
        }

        if (module.hasIniTemplate() || !module.isTransitive())
        {
            // Append to start.ini
            try (BufferedWriter writer = Files.newBufferedWriter(startIni,StandardCharsets.UTF_8,StandardOpenOption.APPEND,StandardOpenOption.CREATE))
            {
                module.writeIniSection(writer, props);
            }
            return baseHome.toShortForm(startIni);
        }

        return null;
    }
