	@Test
	public void multiBind() throws Exception {
		BeanPropertyBindingResult result = new BeanPropertyBindingResult(new TestBean(), "testBean");
		result.getPropertyAccessor().registerCustomEditor(TestBean.class, "friends", new FriendEditor());
		exposeBindingResult(result);

		BindStatus bindStatus = new BindStatus(getRequestContext(), "testBean.friends", false);

		getPageContext().setAttribute(SelectTag.LIST_VALUE_PAGE_ATTRIBUTE, bindStatus);
		this.tag.setValue(new TestBean("foo"));
		this.tag.doStartTag();
		this.tag.doEndTag();

		assertEquals("<option value=\"foo\">foo</option>", getOutput());
	}
