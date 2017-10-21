    private void parseTagDirective(Node parent) throws JasperException {
        Attributes attrs = parseAttributes();
        Node.TagDirective n = new Node.TagDirective(attrs, start, parent);

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
