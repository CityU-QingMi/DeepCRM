    public void write(Modules modules, Path outputFile) throws IOException
    {
        try (BufferedWriter writer = Files.newBufferedWriter(outputFile,StandardCharsets.UTF_8,StandardOpenOption.CREATE_NEW,StandardOpenOption.WRITE); 
             PrintWriter out = new PrintWriter(writer);)
        {
            writeHeaderMessage(out,outputFile);

            out.println();
            out.println("digraph modules {");

            // Node Style
            out.println("  node [color=gray, style=filled, shape=rectangle];");
            out.println("  node [fontname=\"Verdana\", size=\"20,20\"];");
            // Graph Style
            out.println("  graph [");
            out.println("    concentrate=false,");
            out.println("    fontname=\"Verdana\",");
            out.println("    fontsize = 20,");
            out.println("    rankdir = LR,");
            out.println("    ranksep = 1.5,");
            out.println("    nodesep = .5,");
            out.println("    style = bold,");
            out.println("    labeljust = l,");
            out.println("    label = \"Jetty Modules\",");
            out.println("    ssize = \"20,40\"");
            out.println("  ];");

            List<Module> enabled = modules.getEnabled();

            // Module Nodes
            writeModules(out,modules,enabled);

            // Module Relationships
            writeRelationships(out,modules,enabled);

            out.println("}");
            out.println();
        }
    }
