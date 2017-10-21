	private void appendTagDirective(Node.TagDirective n)
	        throws JasperException {

	    boolean append = false;
	    Attributes attrs = n.getAttributes();
	    int len = (attrs == null) ? 0 : attrs.getLength();
	    for (int i=0; i<len; i++) {
		String attrName = attrs.getQName(i);
		if (!"pageEncoding".equals(attrName)) {
		    append = true;
		    break;
		}
	    }
	    if (!append) {
		return;
	    }

	    appendTag(n);
	}
