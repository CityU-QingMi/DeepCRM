        public void visit(Node.AttributeGenerator n) throws JasperException {
            Node.CustomTag tag = n.getTag();
            Node.JspAttribute[] attrs = tag.getJspAttributes();
            for (int i = 0; attrs != null && i < attrs.length; i++) {
                if (attrs[i].getName().equals(n.getName())) {
                    out.print(evaluateAttribute(getTagHandlerInfo(tag),
                            attrs[i], tag, null));
                    break;
                }
            }
        }
