	@Override
	@SuppressWarnings("")
	protected void onSetUp() {
		this.tag = new OptionTag() {
			@Override
			protected TagWriter createTagWriter() {
				return new TagWriter(getWriter());
			}
		};
		this.parentTag = new SelectTag() {
			@Override
			public String getName() {
				// Should not be used other than to delegate to
				// RequestDataValueDataProcessor
				return "testName";
			}
		};
		this.tag.setParent(this.parentTag);
		this.tag.setPageContext(getPageContext());
	}
