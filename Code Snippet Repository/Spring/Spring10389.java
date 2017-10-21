	@Test
	public void favorPath() throws Exception {
		this.factoryBean.setFavorPathExtension(true);
		this.factoryBean.addMediaTypes(Collections.singletonMap("bar", new MediaType("application", "bar")));
		this.factoryBean.afterPropertiesSet();
		ContentNegotiationManager manager = this.factoryBean.getObject();

		this.servletRequest.setRequestURI("/flower.foo");
		assertEquals(Collections.singletonList(new MediaType("application", "foo")),
				manager.resolveMediaTypes(this.webRequest));

		this.servletRequest.setRequestURI("/flower.bar");
		assertEquals(Collections.singletonList(new MediaType("application", "bar")),
				manager.resolveMediaTypes(this.webRequest));

		this.servletRequest.setRequestURI("/flower.gif");
		assertEquals(Collections.singletonList(MediaType.IMAGE_GIF), manager.resolveMediaTypes(this.webRequest));
	}
