	@Override
	public int doEndTag() {
		if (this.previousNestedPath != null) {
			// Expose previous nestedPath value.
			pageContext.setAttribute(NESTED_PATH_VARIABLE_NAME, this.previousNestedPath, PageContext.REQUEST_SCOPE);
		}
		else {
			// Remove exposed nestedPath value.
			pageContext.removeAttribute(NESTED_PATH_VARIABLE_NAME, PageContext.REQUEST_SCOPE);
		}

		return EVAL_PAGE;
	}
