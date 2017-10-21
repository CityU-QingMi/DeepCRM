	@Test
	public void htmlEscapeTag() throws JspException {
		PageContext pc = createPageContext();
		HtmlEscapeTag tag = new HtmlEscapeTag();
		tag.setPageContext(pc);
		tag.doStartTag();
		HtmlEscapingAwareTag testTag = new HtmlEscapingAwareTag() {
			@Override
			public int doStartTagInternal() throws Exception {
				return EVAL_BODY_INCLUDE;
			}
		};
		testTag.setPageContext(pc);
		testTag.doStartTag();

		assertTrue("Correct default", !tag.getRequestContext().isDefaultHtmlEscape());
		assertTrue("Correctly applied", !testTag.isHtmlEscape());
		tag.setDefaultHtmlEscape(true);
		assertTrue("Correct doStartTag return value", tag.doStartTag() == Tag.EVAL_BODY_INCLUDE);
		assertTrue("Correctly enabled", tag.getRequestContext().isDefaultHtmlEscape());
		assertTrue("Correctly applied", testTag.isHtmlEscape());
		tag.setDefaultHtmlEscape(false);
		assertTrue("Correct doStartTag return value", tag.doStartTag() == Tag.EVAL_BODY_INCLUDE);
		assertTrue("Correctly disabled", !tag.getRequestContext().isDefaultHtmlEscape());
		assertTrue("Correctly applied", !testTag.isHtmlEscape());

		tag.setDefaultHtmlEscape(true);
		assertTrue("Correct doStartTag return value", tag.doStartTag() == Tag.EVAL_BODY_INCLUDE);
		testTag.setHtmlEscape(true);
		assertTrue("Correctly enabled", tag.getRequestContext().isDefaultHtmlEscape());
		assertTrue("Correctly applied", testTag.isHtmlEscape());
		testTag.setHtmlEscape(false);
		assertTrue("Correctly enabled", tag.getRequestContext().isDefaultHtmlEscape());
		assertTrue("Correctly applied", !testTag.isHtmlEscape());
		tag.setDefaultHtmlEscape(false);
		assertTrue("Correct doStartTag return value", tag.doStartTag() == Tag.EVAL_BODY_INCLUDE);
		testTag.setHtmlEscape(true);
		assertTrue("Correctly disabled", !tag.getRequestContext().isDefaultHtmlEscape());
		assertTrue("Correctly applied", testTag.isHtmlEscape());
		testTag.setHtmlEscape(false);
		assertTrue("Correctly disabled", !tag.getRequestContext().isDefaultHtmlEscape());
		assertTrue("Correctly applied", !testTag.isHtmlEscape());
	}
