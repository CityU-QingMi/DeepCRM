    private void parseTagFileDirectives(Node parent) throws JasperException {
        reader.setSingleFile(true);
        reader.skipUntil("<");
        while (reader.hasMoreInput()) {
            start = reader.mark();
            if (reader.matches("%--")) {
                parseComment(parent);
            } else if (reader.matches("%@")) {
                parseDirective(parent);
            } else if (reader.matches("jsp:directive.")) {
                parseXMLDirective(parent);
            }
            reader.skipUntil("<");
        }
    }
