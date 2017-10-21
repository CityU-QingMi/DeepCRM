	@Override
	@SuppressWarnings("")
	protected void onSetUp() {
		this.tag = new RadioButtonsTag() {
			@Override
			protected TagWriter createTagWriter() {
				return new TagWriter(getWriter());
			}
		};
		this.tag.setPageContext(getPageContext());
	}
