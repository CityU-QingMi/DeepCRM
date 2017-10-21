		@RequestMapping
		@ResponseBody
		public List<Identifiable> handleSubTypeList() {
			SimpleBean foo = new SimpleBean();
			foo.setId(123L);
			foo.setName("foo");
			SimpleBean bar = new SimpleBean();
			bar.setId(456L);
			bar.setName("bar");
			return Arrays.asList(foo, bar);
		}
