	@Test
	public void testCollectionFieldsDefault() throws Exception {
		TestBean target = new TestBean();
		target.setSomeSet(null);
		target.setSomeList(null);
		target.setSomeMap(null);
		WebRequestDataBinder binder = new WebRequestDataBinder(target);

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("_someSet", "visible");
		request.addParameter("_someList", "visible");
		request.addParameter("_someMap", "visible");

		binder.bind(new ServletWebRequest(request));
		assertThat(target.getSomeSet(), notNullValue());
		assertThat(target.getSomeSet(), isA(Set.class));

		assertThat(target.getSomeList(), notNullValue());
		assertThat(target.getSomeList(), isA(List.class));

		assertThat(target.getSomeMap(), notNullValue());
		assertThat(target.getSomeMap(), isA(Map.class));
	}
