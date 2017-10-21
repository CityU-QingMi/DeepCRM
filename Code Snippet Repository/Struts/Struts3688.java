    public void render(IndentWriter writer) throws IOException {
        // write out the header
        writer.write("digraph mygraph {", true);
        writer.write("fontsize=10;");
        writer.write("fontname=helvetica;");
        writer.write("node [fontsize=10, fontname=helvetica, style=filled, shape=rectangle]");
        writer.write("edge [fontsize=10, fontname=helvetica]");

        // render all the subgraphs
        for (SubGraph subGraph : subGraphs) {
            subGraph.render(new IndentWriter(writer));
        }

        // render all the nodes
        for (SiteGraphNode siteGraphNode : nodes) {
            siteGraphNode.render(writer);
        }

        // finally, render the links
        for (Link link : links) {
            link.render(writer);
        }

        // and now the footer
        writer.write("}", true);
    }
