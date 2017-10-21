    public static void map(Compiler compiler, Node.Nodes page) 
                throws JasperException {

        ELFunctionMapper map = new ELFunctionMapper();
        map.ds = new StringBuffer();
        map.ss = new StringBuffer();

        page.visit(map.new ELFunctionVisitor());

        // Append the declarations to the root node
        String ds = map.ds.toString();
        if (ds.length() > 0) {
            Node root = page.getRoot();
            new Node.Declaration(map.ss.toString(), null, root);
            new Node.Declaration("static {\n" + ds + "}\n", null, root);
        }
    }
