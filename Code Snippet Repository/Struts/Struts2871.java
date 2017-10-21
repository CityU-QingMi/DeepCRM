	private void appendText(String text, boolean createJspTextElement) {
	    if (createJspTextElement) {
		buf.append("<").append(JSP_TEXT_ACTION);
		buf.append("\n");

		// append jsp:id
		buf.append("  ").append(jspIdPrefix).append(":id").append("=\"");
		buf.append(jspId++).append("\"\n");
		buf.append(">\n");

		appendCDATA(text);
		buf.append(JSP_TEXT_ACTION_END);
		buf.append("\n");
	    } else {
		appendCDATA(text);
	    }
	}
