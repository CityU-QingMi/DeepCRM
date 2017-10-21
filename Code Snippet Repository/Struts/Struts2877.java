	public void visit(Node.Root n) throws JasperException {
	    if (n == this.root) {
		// top-level page
		appendXmlProlog();
		appendTag(n);
	    } else {
		boolean resetDefaultNSSave = resetDefaultNS;
		if (n.isXmlSyntax()) {
		    resetDefaultNS = true;
		}
		visitBody(n);
		resetDefaultNS = resetDefaultNSSave;
	    }
	}
