    public void render(IndentWriter writer) throws IOException {
        // write the header
        writer.write("subgraph cluster_" + getPrefix() + " {", true);
        writer.write("color=grey;");
        writer.write("fontcolor=grey;");
        writer.write("label=\"" + name + "\";");

        // write out the subgraphs
        for (SubGraph subGraph : subGraphs) {
            subGraph.render(new IndentWriter(writer));
        }

        // write out the actions
        for (SiteGraphNode siteGraphNode : nodes) {
            siteGraphNode.render(writer);
        }

        // .. footer
        writer.write("}", true);
    }
