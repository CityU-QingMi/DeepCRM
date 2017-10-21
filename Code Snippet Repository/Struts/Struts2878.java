	public void visit(Node.ELExpression n) throws JasperException {
	    if (!n.getRoot().isXmlSyntax()) {
		buf.append("<").append(JSP_TEXT_ACTION);
		buf.append(" ");
	        buf.append(jspIdPrefix);
		buf.append(":id=\"");
		buf.append(jspId++).append("\">");
	    }
	    buf.append("${");
            buf.append(JspUtil.escapeXml(n.getText()));
	    buf.append("}");
	    if (!n.getRoot().isXmlSyntax()) {
		buf.append(JSP_TEXT_ACTION_END);
	    }
	    buf.append("\n");
	}
