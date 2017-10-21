	private String getAttributes(Attributes attrs) {
	    if (attrs == null)
		return "";

	    StringBuffer buf = new StringBuffer();
	    for (int i=0; i < attrs.getLength(); i++) {
		buf.append(" " + attrs.getQName(i) + "=\""
			   + attrs.getValue(i) + "\"");
	    }
	    return buf.toString();
	}
