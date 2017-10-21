        public void visit(Node.ParamAction n) throws JasperException {
            JspUtil.checkAttributes("Param action", n, paramActionAttrs, err);
            // make sure the value of the 'name' attribute is not a
            // request-time expression
            throwErrorIfExpression(n, "name", "jsp:param");
            n.setValue(getJspAttribute(null, "value", null, null, n
                    .getAttributeValue("value"), java.lang.String.class, n,
                    false));
            visitBody(n);
        }
