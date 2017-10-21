        public String getText() {

            class AttributeVisitor extends Visitor {
                String attrValue = null;

                public void visit(TemplateText txt) {
                    attrValue = new String(txt.getText());
                }

                public String getAttrValue() {
                    return attrValue;
                }
            }

            // According to JSP 2.0, if the body of the <jsp:attribute>
            // action is empty, it is equivalent of specifying "" as the value
            // of the attribute.
            String text = "";
            if (getBody() != null) {
                AttributeVisitor attributeVisitor = new AttributeVisitor();
                try {
                    getBody().visit(attributeVisitor);
                } catch (JasperException e) {
                }
                text = attributeVisitor.getAttrValue();
            }

            return text;
        }
