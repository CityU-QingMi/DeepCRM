    private void parseElementsScriptless(Node parent) throws JasperException {
        // Keep track of how many scriptless nodes we've encountered
        // so we know whether our child nodes are forced scriptless
        scriptlessCount++;

        start = reader.mark();
        if (reader.matches("<%--")) {
            parseComment(parent);
        } else if (reader.matches("<%@")) {
            parseDirective(parent);
        } else if (reader.matches("<jsp:directive.")) {
            parseXMLDirective(parent);
        } else if (reader.matches("<%!")) {
            err.jspError(reader.mark(), "jsp.error.no.scriptlets");
        } else if (reader.matches("<jsp:declaration")) {
            err.jspError(reader.mark(), "jsp.error.no.scriptlets");
        } else if (reader.matches("<%=")) {
            err.jspError(reader.mark(), "jsp.error.no.scriptlets");
        } else if (reader.matches("<jsp:expression")) {
            err.jspError(reader.mark(), "jsp.error.no.scriptlets");
        } else if (reader.matches("<%")) {
            err.jspError(reader.mark(), "jsp.error.no.scriptlets");
        } else if (reader.matches("<jsp:scriptlet")) {
            err.jspError(reader.mark(), "jsp.error.no.scriptlets");
        } else if (reader.matches("<jsp:text")) {
            parseXMLTemplateText(parent);
        } else if (reader.matches("${")) {
            parseELExpression(parent, '$');
        } else if (reader.matches("#{")) {
            parseELExpression(parent, '#');
        } else if (reader.matches("<jsp:")) {
            parseStandardAction(parent);
        } else if (!parseCustomTag(parent)) {
            checkUnbalancedEndTag();
            parseTemplateText(parent);
        }

        scriptlessCount--;
    }
