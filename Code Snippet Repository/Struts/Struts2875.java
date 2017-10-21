	public void visit(Node.TaglibDirective n) throws JasperException {
	    Attributes attrs = n.getAttributes();
	    if (attrs != null) {
		String qName = "xmlns:" + attrs.getValue("prefix");
/**/
/**/
/**/
/**/
/**/
/**/
		if (rootAttrs.getIndex(qName) == -1) {
		    String location = attrs.getValue("uri");
		    if (location != null) {
                        if (location.startsWith("/")) {
                            location = URN_JSPTLD + location;
                        }
			rootAttrs.addAttribute("", "", qName, "CDATA",
					       location);
		    } else {
			location = attrs.getValue("tagdir");
			rootAttrs.addAttribute("", "", qName, "CDATA",
					       URN_JSPTAGDIR + location);
		    }
		}
	    }
	}
