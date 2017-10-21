	@Override
	@SuppressWarnings("")
	protected void onSetUp() {
		this.tag = new CheckboxTag() {
			@Override
			protected TagWriter createTagWriter() {
				return new TagWriter(getWriter());
			}
		};
		this.tag.setPageContext(getPageContext());
	}
