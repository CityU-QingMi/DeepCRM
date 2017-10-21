        public void visit(Node.JspElement n) throws JasperException {
            if (n.getNameAttribute().isExpression())
                scriptingElementSeen = true;

            Node.JspAttribute[] attrs = n.getJspAttributes();
            for (int i = 0; i < attrs.length; i++) {
                if (attrs[i].isExpression()) {
                    scriptingElementSeen = true;
                    break;
                }
            }
            visitBody(n);
        }
