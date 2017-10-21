	@Override
	public int doStartTag() throws JspException {
		// Save previous nestedPath value, build and expose current nestedPath value.
		// Use request scope to expose nestedPath to included pages too.
		this.previousNestedPath =
				(String) pageContext.getAttribute(NESTED_PATH_VARIABLE_NAME, PageContext.REQUEST_SCOPE);
		String nestedPath =
				(this.previousNestedPath != null ? this.previousNestedPath + getPath() : getPath());
		pageContext.setAttribute(NESTED_PATH_VARIABLE_NAME, nestedPath, PageContext.REQUEST_SCOPE);

		return EVAL_BODY_INCLUDE;
	}
