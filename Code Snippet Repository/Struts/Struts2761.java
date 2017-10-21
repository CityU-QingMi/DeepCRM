    private void generatePostamble(Node.Nodes page) {
        out.popIndent();
        out.printil("} catch (Throwable t) {");
        out.pushIndent();
        out.printil("if (!(t instanceof SkipPageException)){");
        out.pushIndent();
        out.printil("out = _jspx_out;");
        out.printil("if (out != null && out.getBufferSize() != 0)");
        out.pushIndent();
        out.printil("try { out.clearBuffer(); } catch (java.io.IOException e) {}");
        out.popIndent();

        out
                .printil("if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);");
        out.popIndent();
        out.printil("}");
        out.popIndent();
        out.printil("} finally {");
        out.pushIndent();

        out
                .printil("_jspxFactory.releasePageContext(_jspx_page_context);");

        out.popIndent();
        out.printil("}");

        // Close the service method
        out.popIndent();
        out.printil("}");

        // Generated methods, helper classes, etc.
        genCommonPostamble();
    }
