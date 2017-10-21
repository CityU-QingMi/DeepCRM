	@Override
	@SuppressWarnings("")
	protected void onSetUp() {
		this.tag = new SelectTag() {
			@Override
			protected TagWriter createTagWriter() {
				return new TagWriter(getWriter());
			}
		};
		this.tag.setPageContext(getPageContext());
	}
