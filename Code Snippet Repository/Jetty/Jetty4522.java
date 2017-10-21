    private void writeRelationships(PrintWriter out, Iterable<Module> modules, List<Module> enabled)
    {
        for (Module module : modules)
        {
            for (String depends : module.getDepends())
                out.printf("    \"%s\" -> \"%s\";%n",module.getName(),depends);
            for (String optional : module.getOptional())
                out.printf("    \"%s\" => \"%s\";%n",module.getName(),optional);
        }
    }
