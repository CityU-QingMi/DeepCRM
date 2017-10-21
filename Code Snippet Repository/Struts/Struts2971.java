    public void apply(Node.Nodes page, ErrorDispatcher err, PageInfo pageInfo)
	    throws JasperException {

	init(err);
	if (tagPlugins == null || tagPlugins.size() == 0) {
	    return;
	}

	this.pageInfo = pageInfo;

        page.visit(new Node.Visitor() {
            public void visit(Node.CustomTag n)
                    throws JasperException {
                invokePlugin(n);
                visitBody(n);
            }
        });

    }
