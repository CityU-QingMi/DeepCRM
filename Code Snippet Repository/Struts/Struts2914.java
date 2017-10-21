    private void parseElementsTemplateText(Node parent) throws JasperException {
        start = reader.mark();
        if (reader.matches("<%--")) {
            parseComment(parent);
        } else if (reader.matches("<%@")) {
            parseDirective(parent);
        } else if (reader.matches("<jsp:directive.")) {
            parseXMLDirective(parent);
        } else if (reader.matches("<%!")) {
            err.jspError(reader.mark(), "jsp.error.not.in.template",
                    "Declarations");
        } else if (reader.matches("<jsp:declaration")) {
            err.jspError(reader.mark(), "jsp.error.not.in.template",
                    "Declarations");
        } else if (reader.matches("<%=")) {
            err.jspError(reader.mark(), "jsp.error.not.in.template",
                    "Expressions");
        } else if (reader.matches("<jsp:expression")) {
            err.jspError(reader.mark(), "jsp.error.not.in.template",
                    "Expressions");
        } else if (reader.matches("<%")) {
            err.jspError(reader.mark(), "jsp.error.not.in.template",
                    "Scriptlets");
        } else if (reader.matches("<jsp:scriptlet")) {
            err.jspError(reader.mark(), "jsp.error.not.in.template",
                    "Scriptlets");
        } else if (reader.matches("<jsp:text")) {
            err.jspError(reader.mark(), "jsp.error.not.in.template",
                    "&lt;jsp:text");
        } else if (reader.matches("${")) {
            err.jspError(reader.mark(), "jsp.error.not.in.template",
                    "Expression language");
        } else if (reader.matches("#{")) {
            err.jspError(reader.mark(), "jsp.error.not.in.template",
                    "Expression language");
        } else if (reader.matches("<jsp:")) {
            err.jspError(reader.mark(), "jsp.error.not.in.template",
                    "Standard actions");
        } else if (parseCustomTag(parent)) {
            err.jspError(reader.mark(), "jsp.error.not.in.template",
                    "Custom actions");
        } else {
            checkUnbalancedEndTag();
            parseTemplateText(parent);
        }
    }
