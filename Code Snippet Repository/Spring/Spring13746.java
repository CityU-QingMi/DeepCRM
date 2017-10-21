	@Test
	public void testResolve() throws Exception {
		given(this.renderer.isRenderable(eq("/template.test"), isA(Request.class))).willReturn(true);
		given(this.renderer.isRenderable(eq("/nonexistent.test"), isA(Request.class))).willReturn(false);

		assertTrue(this.viewResolver.resolveViewName("/template.test", Locale.ITALY) instanceof TilesView);
		assertNull(this.viewResolver.resolveViewName("/nonexistent.test", Locale.ITALY));

		verify(this.renderer).isRenderable(eq("/template.test"), isA(Request.class));
		verify(this.renderer).isRenderable(eq("/nonexistent.test"), isA(Request.class));
	}
