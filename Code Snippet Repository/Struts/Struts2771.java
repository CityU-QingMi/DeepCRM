    private void genPreambleStaticInitializers() throws JasperException {
        out.printil("private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();");
        out.println();

        // Static data for getDependants()
        out.printil("private static java.util.List _jspx_dependants;");
        out.println();
        List dependants = pageInfo.getDependants();
        Iterator iter = dependants.iterator();
        if (!dependants.isEmpty()) {
            out.printil("static {");
            out.pushIndent();
            out.printin("_jspx_dependants = new java.util.ArrayList(");
            out.print("" + dependants.size());
            out.println(");");
            while (iter.hasNext()) {
                out.printin("_jspx_dependants.add(\"");
                out.print((String) iter.next());
                out.println("\");");
            }
            out.popIndent();
            out.printil("}");
            out.println();
        }
    }
