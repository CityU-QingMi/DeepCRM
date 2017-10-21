    public static Node.Nodes parse(ParserController pc, JspReader reader,
            Node parent, boolean isTagFile, boolean directivesOnly,
            URL jarFileUrl, String pageEnc, String jspConfigPageEnc,
            boolean isDefaultPageEncoding, boolean isBomPresent) throws JasperException {

        Parser parser = new Parser(pc, reader, isTagFile, directivesOnly,
                jarFileUrl);

        Node.Root root = new Node.Root(reader.mark(), parent, false);
        root.setPageEncoding(pageEnc);
        root.setJspConfigPageEncoding(jspConfigPageEnc);
        root.setIsDefaultPageEncoding(isDefaultPageEncoding);
        root.setIsBomPresent(isBomPresent);

        if (directivesOnly) {
            parser.parseTagFileDirectives(root);
            return new Node.Nodes(root);
        }

        // For the Top level page, add inlcude-prelude and include-coda
        PageInfo pageInfo = pc.getCompiler().getPageInfo();
        if (parent == null) {
            parser.addInclude(root, pageInfo.getIncludePrelude());
        }
        while (reader.hasMoreInput()) {
            parser.parseElements(root);
        }
        if (parent == null) {
            parser.addInclude(root, pageInfo.getIncludeCoda());
        }

        Node.Nodes page = new Node.Nodes(root);
        return page;
    }
