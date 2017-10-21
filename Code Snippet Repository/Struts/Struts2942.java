    public static void evaluateNodes(
        Node.Nodes nodes,
        SmapStratum s,
        HashMap innerClassMap,
        boolean breakAtLF) {
        try {
            nodes.visit(new SmapGenVisitor(s, breakAtLF, innerClassMap));
        } catch (JasperException ex) {
        }
    }
