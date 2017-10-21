    private void parsePageDirective(Node parent) throws JasperException {
        Attributes attrs = parseAttributes();
        Node.PageDirective n = new Node.PageDirective(attrs, start, parent);

/**/
/**/
/**/
/**/
/**/
        for (int i = 0; i < attrs.getLength(); i++) {
            if ("import".equals(attrs.getQName(i))) {
                n.addImport(attrs.getValue(i));
            }
        }
    }
