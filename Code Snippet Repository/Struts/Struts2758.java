        public String generateNamedAttributeJspFragment(Node.NamedAttribute n,
                String tagHandlerVar) throws JasperException {
            String varName = n.getTemporaryVariableName();

            out.printin("javax.servlet.jsp.tagext.JspFragment " + varName
                    + " = ");
            generateJspFragment(n, tagHandlerVar);
            out.println(";");

            return varName;
        }
