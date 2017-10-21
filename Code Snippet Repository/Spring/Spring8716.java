	@Before
	public void setUp() throws Exception {
		this.matchers = new ModelResultMatchers();

		ModelAndView mav = new ModelAndView("view", "good", "good");
		BindingResult bindingResult = new BeanPropertyBindingResult("good", "good");
		mav.addObject(BindingResult.MODEL_KEY_PREFIX + "good", bindingResult);

		this.mvcResult = getMvcResult(mav);

		Date date = new Date();
		BindingResult bindingResultWithError = new BeanPropertyBindingResult(date, "date");
		bindingResultWithError.rejectValue("time", "error");

		ModelAndView mavWithError = new ModelAndView("view", "good", "good");
		mavWithError.addObject("date", date);
		mavWithError.addObject(BindingResult.MODEL_KEY_PREFIX + "date", bindingResultWithError);

		this.mvcResultWithError = getMvcResult(mavWithError);
	}
