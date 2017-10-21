	public JspContextWrapper(JspContext jspContext, ArrayList nestedVars,
			ArrayList atBeginVars, ArrayList atEndVars, Map aliases) {
		this.invokingJspCtxt = (PageContext) jspContext;
		this.nestedVars = nestedVars;
		this.atBeginVars = atBeginVars;
		this.atEndVars = atEndVars;
		this.pageAttributes = new HashMap<String, Object>(16);
		this.aliases = aliases;

		if (nestedVars != null) {
			this.originalNestedVars = new HashMap<String, Object>(nestedVars.size());
		}
		syncBeginTagFile();
	}
