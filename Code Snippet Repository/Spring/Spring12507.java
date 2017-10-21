	@Override
	protected AbstractUrlBasedView buildView(String viewName) throws Exception {
		XsltView view = (XsltView) super.buildView(viewName);
		if (this.sourceKey != null) {
			view.setSourceKey(this.sourceKey);
		}
		if (this.uriResolver != null) {
			view.setUriResolver(this.uriResolver);
		}
		if (this.errorListener != null) {
			view.setErrorListener(this.errorListener);
		}
		view.setIndent(this.indent);
		if (this.outputProperties != null) {
			view.setOutputProperties(this.outputProperties);
		}
		view.setCacheTemplates(this.cacheTemplates);
		return view;
	}
