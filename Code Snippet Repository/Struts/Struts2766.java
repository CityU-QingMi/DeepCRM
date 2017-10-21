        public void generatePreamble() {
            ServletWriter out = this.classBuffer.getOut();
            out.println();
            out.pushIndent();
            // Note: cannot be static, as we need to reference things like
            // _jspx_meth_*
            out.printil("private class " + className);
            out.printil("    extends "
                    + "org.apache.struts2.jasper.runtime.JspFragmentHelper");
            out.printil("{");
            out.pushIndent();
            out
                    .printil("private javax.servlet.jsp.tagext.JspTag _jspx_parent;");
            out.printil("private int[] _jspx_push_body_count;");
            out.println();
            out.printil("public " + className
                    + "( int discriminator, JspContext jspContext, "
                    + "javax.servlet.jsp.tagext.JspTag _jspx_parent, "
                    + "int[] _jspx_push_body_count ) {");
            out.pushIndent();
            out.printil("super( discriminator, jspContext, _jspx_parent );");
            out.printil("this._jspx_parent = _jspx_parent;");
            out.printil("this._jspx_push_body_count = _jspx_push_body_count;");
            out.popIndent();
            out.printil("}");
        }
