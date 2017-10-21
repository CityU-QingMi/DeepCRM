		@RequestMapping
		@ResponseBody
		@JsonView(MyJacksonView2.class)
		public JacksonViewBean handleResponseBody() {
			JacksonViewBean bean = new JacksonViewBean();
			bean.setWithView1("with");
			bean.setWithView2("with");
			bean.setWithoutView("without");
			return bean;
		}
