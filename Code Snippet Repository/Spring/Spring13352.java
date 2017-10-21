	@Test
	public void transformTagWithHtmlEscape() throws JspException {
		// first set up the PageContext and the bean
		PageContext pc = createPageContext();
		TestBean tb = new TestBean();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		ServletRequestDataBinder binder = new ServletRequestDataBinder(tb, "tb");
		CustomDateEditor l = new CustomDateEditor(df, true);
		binder.registerCustomEditor(Date.class, l);
		pc.getRequest().setAttribute(BindingResult.MODEL_KEY_PREFIX + "tb", binder.getBindingResult());

		// try another time, this time using Strings
		BindTag bind = new BindTag();
		bind.setPageContext(pc);
		bind.setPath("tb.name");
		bind.doStartTag();

		TransformTag transform = new TransformTag();
		transform.setPageContext(pc);
		transform.setValue("na<me");
		transform.setParent(bind);
		transform.setVar("theString");
		transform.setHtmlEscape(true);
		transform.doStartTag();

		assertNotNull(pc.getAttribute("theString"));
		assertEquals("na&lt;me", pc.getAttribute("theString"));
	}
