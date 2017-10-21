    private void genPreambleClassVariableDeclarations(String className)
            throws JasperException {
        if (isPoolingEnabled && !tagHandlerPoolNames.isEmpty()) {
            for (int i = 0; i < tagHandlerPoolNames.size(); i++) {
                out.printil("private org.apache.struts2.jasper.runtime.TagHandlerPool "
                        + tagHandlerPoolNames.elementAt(i) + ";");
            }
            out.println();
        }
        out.printin("private javax.el.ExpressionFactory ");
        out.print(VAR_EXPRESSIONFACTORY);
        out.println(";");
        out.printin("private org.apache.AnnotationProcessor ");
        out.print(VAR_ANNOTATIONPROCESSOR);
        out.println(";");
        out.println();
    }
