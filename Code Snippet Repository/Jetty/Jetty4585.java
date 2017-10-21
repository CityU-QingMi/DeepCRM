    @Override
    public String addModule(Module module, Props props) throws IOException
    {
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
            // Create start.d/{name}.ini
            Path ini = startDir.resolve(module.getName() + ".ini");
            try (BufferedWriter writer = Files.newBufferedWriter(ini,StandardCharsets.UTF_8,StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING))
            {
                module.writeIniSection(writer,props);
            }
            return baseHome.toShortForm(ini);
        }

        return null;
    }
