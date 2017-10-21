	@Test
	public void transformTagOutsideBindTag() throws JspException {
		// first set up the pagecontext and the bean
		PageContext pc = createPageContext();
		TestBean tb = new TestBean();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		ServletRequestDataBinder binder = new ServletRequestDataBinder(tb, "tb");
		CustomDateEditor l = new CustomDateEditor(df, true);
		binder.registerCustomEditor(Date.class, l);
		pc.getRequest().setAttribute(BindingResult.MODEL_KEY_PREFIX + "tb", binder.getBindingResult());

		// now try to execute the tag outside a bindtag
		TransformTag transform = new TransformTag();
		transform.setPageContext(pc);
		transform.setVar("var");
		transform.setValue("bla");
		try {
			transform.doStartTag();
			fail("Tag can be executed outside BindTag");
		}
		catch (JspException e) {
			// this is ok!
		}

		// now try to execute the tag outside a bindtag, but inside a messageTag
		MessageTag message = new MessageTag();
		message.setPageContext(pc);
		transform = new TransformTag();
		transform.setPageContext(pc);
		transform.setVar("var");
		transform.setValue("bla");
		transform.setParent(message);
		try {
			transform.doStartTag();
			fail("Tag can be executed outside BindTag and inside messagtag");
		}
		catch (JspException e) {
			// this is ok!
		}
	}
