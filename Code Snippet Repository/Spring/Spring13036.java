		@RequestMapping
		@JsonView(MyJacksonView2.class)
		public ResponseEntity<JacksonViewBean> handleResponseEntity() {
			JacksonViewBean bean = new JacksonViewBean();
			bean.setWithView1("with");
			bean.setWithView2("with");
			bean.setWithoutView("without");
			ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
			mav.addObject("bean", bean);
			return new ResponseEntity<>(bean, HttpStatus.OK);
		}
