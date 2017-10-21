	@Override
	@SuppressWarnings("")
	protected void onSetUp() {
		this.tag = new OptionsTag() {
			@Override
			protected TagWriter createTagWriter() {
				return new TagWriter(getWriter());
			}
		};
		selectTag = new SelectTag() {
			@Override
			protected TagWriter createTagWriter() {
				return new TagWriter(getWriter());
			}
			@Override
			public String getName() {
				// Should not be used other than to delegate to
				// RequestDataValueDataProcessor
				return "testName";
			}
		};
		selectTag.setPageContext(getPageContext());
		this.tag.setParent(selectTag);
		this.tag.setPageContext(getPageContext());
	}
