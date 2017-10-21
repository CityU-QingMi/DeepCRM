    public PageDataImpl(Node.Nodes page, Compiler compiler)
	        throws JasperException {

	// First pass
	FirstPassVisitor firstPass = new FirstPassVisitor(page.getRoot(),
							  compiler.getPageInfo());
	page.visit(firstPass);

	// Second pass
	buf = new StringBuffer();
	SecondPassVisitor secondPass
	    = new SecondPassVisitor(page.getRoot(), buf, compiler,
				    firstPass.getJspIdPrefix());
	page.visit(secondPass);
    }
