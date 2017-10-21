    private static void generateLocalVariables(ServletWriter out, Node n)
            throws JasperException {
        Node.ChildInfo ci;
        if (n instanceof Node.CustomTag) {
            ci = ((Node.CustomTag) n).getChildInfo();
        } else if (n instanceof Node.JspBody) {
            ci = ((Node.JspBody) n).getChildInfo();
        } else if (n instanceof Node.NamedAttribute) {
            ci = ((Node.NamedAttribute) n).getChildInfo();
        } else {
            // Cannot access err since this method is static, but at
            // least flag an error.
            throw new JasperException("Unexpected Node Type");
            // err.getString(
            // "jsp.error.internal.unexpected_node_type" ) );
        }

        if (ci.hasUseBean()) {
            out
                    .printil("HttpSession session = _jspx_page_context.getSession();");
            out
                    .printil("ServletContext application = _jspx_page_context.getServletContext();");
        }
        if (ci.hasUseBean() || ci.hasIncludeAction() || ci.hasSetProperty()
                || ci.hasParamAction()) {
            out
                    .printil("HttpServletRequest request = (HttpServletRequest)_jspx_page_context.getRequest();");
        }
        if (ci.hasIncludeAction()) {
            out
                    .printil("HttpServletResponse response = (HttpServletResponse)_jspx_page_context.getResponse();");
        }
    }
