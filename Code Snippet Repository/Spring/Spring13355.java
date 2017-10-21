	@Test
	public void transformTagWithSettingOfScope() throws JspException {
		// first set up the pagecontext and the bean
		PageContext pc = createPageContext();
		TestBean tb = new TestBean();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		ServletRequestDataBinder binder = new ServletRequestDataBinder(tb, "tb");
		CustomDateEditor l = new CustomDateEditor(df, true);
		binder.registerCustomEditor(Date.class, l);
		pc.getRequest().setAttribute(BindingResult.MODEL_KEY_PREFIX + "tb", binder.getBindingResult());

		// execute the bind tag using the date property
		BindTag bind = new BindTag();
		bind.setPageContext(pc);
		bind.setPath("tb.date");
		bind.doStartTag();

		// transform stuff
		TransformTag transform = new TransformTag();
		transform.setPageContext(pc);
		transform.setParent(bind);
		transform.setValue(tb.getDate());
		transform.setVar("theDate");
		transform.setScope("page");
		transform.doStartTag();

		transform.release();

		assertNotNull(pc.getAttribute("theDate"));
		assertEquals(df.format(tb.getDate()), pc.getAttribute("theDate"));

		// try another time, this time using Strings
		bind = new BindTag();
		bind.setPageContext(pc);
		bind.setPath("tb.name");
		bind.doStartTag();

		transform = new TransformTag();
		transform.setPageContext(pc);
		transform.setValue("name");
		transform.setParent(bind);
		transform.setVar("theString");
		transform.setScope("page");
		transform.doStartTag();

		transform.release();

		assertNotNull(pc.getAttribute("theString"));
		assertEquals("name", pc.getAttribute("theString"));
	}
