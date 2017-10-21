	@Override
	@SuppressWarnings("")
	protected void onSetUp() {
		this.tag = new CheckboxesTag() {
			@Override
			protected TagWriter createTagWriter() {
				return new TagWriter(getWriter());
			}
		};
		this.tag.setPageContext(getPageContext());
	}
