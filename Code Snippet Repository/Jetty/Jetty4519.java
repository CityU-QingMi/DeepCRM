    private void writeHeaderMessage(PrintWriter out, Path outputFile)
    {
        out.println("/*");
        out.println(" * GraphViz Graph of Jetty Modules");
        out.println(" * ");
        out.println(" * Jetty: http://eclipse.org/jetty/");
        out.println(" * GraphViz: http://graphviz.org/");
        out.println(" * ");
        out.println(" * To Generate Graph image using graphviz:");
        String filename = outputFile.getFileName().toString();
        String basename = filename.substring(0,filename.indexOf('.'));
        out.printf(" *   $ dot -Tpng -Goverlap=false -o %s.png %s%n",basename,filename);
        out.println(" */");
    }
