	@Test
	void convertsMethodSelectors() {
		options.setSelectedMethods(asList("com.acme.Foo#m()", "com.example.Bar#method(java.lang.Object)"));

		LauncherDiscoveryRequest request = convert();
		List<MethodSelector> methodSelectors = request.getSelectorsByType(MethodSelector.class);

		assertThat(methodSelectors).hasSize(2);
		assertThat(methodSelectors.get(0).getClassName()).isEqualTo("com.acme.Foo");
		assertThat(methodSelectors.get(0).getMethodName()).isEqualTo("m");
		assertThat(methodSelectors.get(0).getMethodParameterTypes()).isEqualTo("");
		assertThat(methodSelectors.get(1).getClassName()).isEqualTo("com.example.Bar");
		assertThat(methodSelectors.get(1).getMethodName()).isEqualTo("method");
		assertThat(methodSelectors.get(1).getMethodParameterTypes()).isEqualTo("java.lang.Object");
	}
