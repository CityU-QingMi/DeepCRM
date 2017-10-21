        private void printParams(Node n, String pageParam, boolean literal)
                throws JasperException {

            class ParamVisitor extends Node.Visitor {
                String separator;

                ParamVisitor(String separator) {
                    this.separator = separator;
                }

                public void visit(Node.ParamAction n) throws JasperException {

                    out.print(" + ");
                    out.print(separator);
                    out.print(" + ");
                    out.print("org.apache.struts2.jasper.runtime.JspRuntimeLibrary."
                            + "URLEncode(" + quote(n.getTextAttribute("name"))
                            + ", request.getCharacterEncoding())");
                    out.print("+ \"=\" + ");
                    out.print(attributeValue(n.getValue(), true, String.class));

                    // The separator is '&' after the second use
                    separator = "\"&\"";
                }
            }

            String sep;
            if (literal) {
                sep = pageParam.indexOf('?') > 0 ? "\"&\"" : "\"?\"";
            } else {
                sep = "((" + pageParam + ").indexOf('?')>0? '&': '?')";
            }
            if (n.getBody() != null) {
                n.getBody().visit(new ParamVisitor(sep));
            }
        }
