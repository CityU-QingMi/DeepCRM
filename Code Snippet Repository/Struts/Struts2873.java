	private void printAttributes(Node n, boolean addDefaultNS) {

/**/
/**/
/**/
	    Attributes attrs = n.getTaglibAttributes();
	    int len = (attrs == null) ? 0 : attrs.getLength();
	    for (int i=0; i<len; i++) {
		String name = attrs.getQName(i);
		String value = attrs.getValue(i);
		buf.append("  ").append(name).append("=\"").append(value).append("\"\n");
	    }

/**/
/**/
/**/
	    attrs = n.getNonTaglibXmlnsAttributes();
	    len = (attrs == null) ? 0 : attrs.getLength();
	    boolean defaultNSSeen = false;
	    for (int i=0; i<len; i++) {
		String name = attrs.getQName(i);
		String value = attrs.getValue(i);
		buf.append("  ").append(name).append("=\"").append(value).append("\"\n");
		defaultNSSeen |= "xmlns".equals(name);
	    }
	    if (addDefaultNS && !defaultNSSeen) {
		buf.append("  xmlns=\"\"\n");
	    }
	    resetDefaultNS = false;

/**/
/**/
/**/
	    attrs = n.getAttributes();
	    len = (attrs == null) ? 0 : attrs.getLength();
	    for (int i=0; i<len; i++) {
		String name = attrs.getQName(i);
		String value = attrs.getValue(i);
		buf.append("  ").append(name).append("=\"");
		buf.append(JspUtil.getExprInXml(value)).append("\"\n");
	    }
	}
