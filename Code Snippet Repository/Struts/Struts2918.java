    private void parseJspBody(Node parent, String bodyType)
            throws JasperException {
        Mark start = reader.mark();
        Node bodyNode = new Node.JspBody(start, parent);

        reader.skipSpaces();
        if (!reader.matches("/>")) {
            if (!reader.matches(">")) {
                err.jspError(start, "jsp.error.unterminated", "&lt;jsp:body");
            }
            parseBody(bodyNode, "jsp:body", bodyType);
        }
    }
