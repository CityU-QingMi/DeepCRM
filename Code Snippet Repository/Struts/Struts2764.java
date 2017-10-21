    public void generateSetDynamicAttribute() {
        out
                .printil("public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {");
        out.pushIndent();
/**/
/**/
/**/
/**/
        out.printil("if (uri == null)");
        out.pushIndent();
        out.printil("_jspx_dynamic_attrs.put(localName, value);");
        out.popIndent();
        out.popIndent();
        out.printil("}");
    }
