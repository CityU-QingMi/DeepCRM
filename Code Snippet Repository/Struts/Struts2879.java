	private void appendTag(Node n, boolean addDefaultNS)
		throws JasperException {

	    Node.Nodes body = n.getBody();
	    String text = n.getText();

	    buf.append("<").append(n.getQName());
	    buf.append("\n");

	    printAttributes(n, addDefaultNS);
	    buf.append("  ").append(jspIdPrefix).append(":id").append("=\"");
	    buf.append(jspId++).append("\"\n");

	    if (ROOT_ACTION.equals(n.getLocalName()) || body != null
		        || text != null) {
		buf.append(">\n");
		if (ROOT_ACTION.equals(n.getLocalName())) {
		    if (compiler.getCompilationContext().isTagFile()) {
			appendTagDirective();
		    } else {
			appendPageDirective();
		    }
		}
		if (body != null) {
		    body.visit(this);
		} else {
		    appendText(text, false);
		}
		buf.append("</" + n.getQName() + ">\n");
	    } else {
		buf.append("/>\n");
	    }
	}
