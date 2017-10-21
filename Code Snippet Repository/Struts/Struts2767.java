    private void generateDestroy() {

        out.printil("public void _jspDestroy() {");
        out.pushIndent();
        
        if (isPoolingEnabled) {
            for (int i = 0; i < tagHandlerPoolNames.size(); i++) {
                                out.printin((String) tagHandlerPoolNames.elementAt(i));
                                out.println(".release();");
            }
        }
        
        out.popIndent();
        out.printil("}");
        out.println();
    }
