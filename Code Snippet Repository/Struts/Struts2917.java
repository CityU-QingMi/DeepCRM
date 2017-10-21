    private void parseTagDependentBody(Node parent, String tag)
            throws JasperException {
        Mark bodyStart = reader.mark();
        Mark bodyEnd = reader.skipUntilETag(tag);
        if (bodyEnd == null) {
            err.jspError(start, "jsp.error.unterminated", "&lt;" + tag);
        }
        new Node.TemplateText(reader.getText(bodyStart, bodyEnd), bodyStart,
                parent);
    }
