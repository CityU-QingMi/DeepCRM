	public void visit(Node.Root n) throws JasperException {
	    visitBody(n);
	    if (n == root) {
/**/
/**/
/**/
/**/
/**/
/**/
/**/
		if (!JSP_URI.equals(rootAttrs.getValue("xmlns:jsp"))) {
		    rootAttrs.addAttribute("", "", "xmlns:jsp", "CDATA",
					   JSP_URI);
		}

		if (pageInfo.isJspPrefixHijacked()) {
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
		    jspIdPrefix += "jsp";
		    while (pageInfo.containsPrefix(jspIdPrefix)) {
			jspIdPrefix += "jsp";
		    }
		    rootAttrs.addAttribute("", "", "xmlns:" + jspIdPrefix,
					   "CDATA", JSP_URI);
		}

		root.setAttributes(rootAttrs);
	    }
	}
