        public void visit(Node.IncludeAction n) throws JasperException {

            String flush = n.getTextAttribute("flush");
            Node.JspAttribute page = n.getPage();

            boolean isFlush = false; // default to false;
            if ("true".equals(flush))
                isFlush = true;

            n.setBeginJavaLine(out.getJavaLine());

            String pageParam;
            if (page.isNamedAttribute()) {
                // If the page for jsp:include was specified via
                // jsp:attribute, first generate code to evaluate
                // that body.
                pageParam = generateNamedAttributeValue(page
                        .getNamedAttributeNode());
            } else {
                pageParam = attributeValue(page, false, String.class);
            }

            // If any of the params have their values specified by
            // jsp:attribute, prepare those values first.
            Node jspBody = findJspBody(n);
            if (jspBody != null) {
                prepareParams(jspBody);
            } else {
                prepareParams(n);
            }

           out.printin(
                    JSPRuntime.class.getName() + ".handle("
                            + pageParam);
            printParams(n, pageParam, page.isLiteral());
            out.println(", " + isFlush + ");");

            n.setEndJavaLine(out.getJavaLine());
        }
