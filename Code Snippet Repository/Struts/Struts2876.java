	private void addAttributes(Attributes attrs) {
	    if (attrs != null) {
		int len = attrs.getLength();

		for (int i=0; i<len; i++) {
                    String qName = attrs.getQName(i);
		    if ("version".equals(qName)) {
			continue;
		    }

                    // Bugzilla 35252: http://issues.apache.org/bugzilla/show_bug.cgi?id=35252
                    if(rootAttrs.getIndex(qName) == -1) {
                        rootAttrs.addAttribute(attrs.getURI(i),
                                               attrs.getLocalName(i),
                                               qName,
                                               attrs.getType(i),
                                               attrs.getValue(i));
                    }
		}
	    }
	}
