	private void appendTagDirective() {
	    buf.append("<").append(JSP_TAG_DIRECTIVE_ACTION);
	    buf.append("\n");

	    // append jsp:id
	    buf.append("  ").append(jspIdPrefix).append(":id").append("=\"");
	    buf.append(jspId++).append("\"\n");
	    buf.append("  ").append("pageEncoding").append("=\"UTF-8\"\n");
	    buf.append("/>\n");	    
	}
