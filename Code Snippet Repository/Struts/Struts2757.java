        public String generateNamedAttributeValue(Node.NamedAttribute n)
                throws JasperException {

            String varName = n.getTemporaryVariableName();

            // If the only body element for this named attribute node is
            // template text, we need not generate an extra call to
            // pushBody and popBody. Maybe we can further optimize
            // here by getting rid of the temporary variable, but in
            // reality it looks like javac does this for us.
            Node.Nodes body = n.getBody();
            if (body != null) {
                boolean templateTextOptimization = false;
                if (body.size() == 1) {
                    Node bodyElement = body.getNode(0);
                    if (bodyElement instanceof Node.TemplateText) {
                        templateTextOptimization = true;
                        out.printil("String "
                                + varName
                                + " = "
                                + quote(new String(
                                        ((Node.TemplateText) bodyElement)
                                                .getText())) + ";");
                    }
                }

                // XXX - Another possible optimization would be for
                // lone EL expressions (no need to pushBody here either).

                if (!templateTextOptimization) {
                    out.printil("out = _jspx_page_context.pushBody();");
                    visitBody(n);
                    out.printil("String " + varName + " = "
                            + "((javax.servlet.jsp.tagext.BodyContent)"
                            + "out).getString();");
                    out.printil("out = _jspx_page_context.popBody();");
                }
            } else {
                // Empty body must be treated as ""
                out.printil("String " + varName + " = \"\";");
            }

            return varName;
        }
