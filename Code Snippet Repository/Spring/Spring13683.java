	@Test
	public void testCacheRemoval() throws Exception {
		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		wac.setServletContext(new MockServletContext());
		wac.refresh();
		InternalResourceViewResolver vr = new InternalResourceViewResolver();
		vr.setViewClass(JstlView.class);
		vr.setApplicationContext(wac);

		View view = vr.resolveViewName("example1", Locale.getDefault());
		View cached = vr.resolveViewName("example1", Locale.getDefault());
		if (view != cached) {
			fail("Caching doesn't work");
		}

		vr.removeFromCache("example1", Locale.getDefault());
		cached = vr.resolveViewName("example1", Locale.getDefault());
		if (view == cached) {
			// the chance of having the same reference (hashCode) twice if negligible).
			fail("View wasn't removed from cache");
		}
	}
