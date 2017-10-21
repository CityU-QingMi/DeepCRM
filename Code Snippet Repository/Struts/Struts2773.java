    private void genPreambleMethods() throws JasperException {
        // Method used to get compile time file dependencies
        out.printil("public Object getDependants() {");
        out.pushIndent();
        out.printil("return _jspx_dependants;");
        out.popIndent();
        out.printil("}");
        out.println();
        
        generateInit();
        generateDestroy();
    }
