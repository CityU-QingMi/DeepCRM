    private void generateInit() {

        if (ctxt.isTagFile()) {
            out.printil("private void _jspInit(ServletConfig config) {");
        } else {
            out.printil("public void _jspInit() {");
        }

        out.pushIndent();
        if (isPoolingEnabled) {
            for (int i = 0; i < tagHandlerPoolNames.size(); i++) {
                out.printin(tagHandlerPoolNames.elementAt(i));
                out.print(" = org.apache.struts2.jasper.runtime.TagHandlerPool.getTagHandlerPool(");
                if (ctxt.isTagFile()) {
                    out.print("config");
                } else {
                    out.print("getServletConfig()");
                }
                out.println(");");
            }
        }
        
        out.printin(VAR_EXPRESSIONFACTORY);
        out.print(" = _jspxFactory.getJspApplicationContext(");
        if (ctxt.isTagFile()) {
            out.print("config");
        } else {
            out.print("getServletConfig()");
        }
        out.println(".getServletContext()).getExpressionFactory();");

        out.printin(VAR_ANNOTATIONPROCESSOR);
        out.print(" = (org.apache.AnnotationProcessor) ");
        if (ctxt.isTagFile()) {
            out.print("config");
        } else {
            out.print("getServletConfig()");
        }
        out.println(".getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());");

        out.popIndent();
        out.printil("}");
        out.println();
    }
