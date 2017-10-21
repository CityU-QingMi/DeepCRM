    private static void writeNode(PrintWriter out, Node node)
    {
        out.println();
        out.println("  // Node");
        out.printf("  \"%s\" [%n",toId(node));
        out.printf("    label=\"%s\",%n",node.getName());
        if (node.getName().endsWith("ed"))
        {
            out.println("    color=\"#ddddff\",");
            out.println("    style=filled,");
        }
        out.println("    shape=box");
        out.println("  ];");
    }
