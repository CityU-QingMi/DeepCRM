    private void writeModules(PrintWriter out, Modules allmodules, List<Module> enabled)
    {
        out.println();
        out.println("  /* Modules */");
        out.println();

        out.println("  node [ labeljust = l ];");

        for (Module module: allmodules)
        {
            boolean resolved = enabled.contains(module);
            writeModuleNode(out,module,resolved);
        }
    }
