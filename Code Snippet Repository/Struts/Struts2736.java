        private String attributeValue(Node.JspAttribute attr, boolean encode,
                Class expectedType) {
            String v = attr.getValue();
            if (!attr.isNamedAttribute() && (v == null))
                return "";

            if (attr.isExpression()) {
                if (encode) {
                    return "org.apache.struts2.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf("
                            + v + "), request.getCharacterEncoding())";
                }
                return v;
            } else if (attr.isELInterpreterInput()) {
                v = attributeValueWithEL(this.isTagFile, v, expectedType,
                        attr.getEL().getMapName());
                if (encode) {
                    return "org.apache.struts2.jasper.runtime.JspRuntimeLibrary.URLEncode("
                            + v + ", request.getCharacterEncoding())";
                }
                return v;
            } else if (attr.isNamedAttribute()) {
                return attr.getNamedAttributeNode().getTemporaryVariableName();
            } else {
                if (encode) {
                    return "org.apache.struts2.jasper.runtime.JspRuntimeLibrary.URLEncode("
                            + quote(v) + ", request.getCharacterEncoding())";
                }
                return quote(v);
            }
        }
