	@Test
	public void multipleMatchesNotSupported() throws Exception {
		this.view = new HttpMessageWriterView(CharSequenceEncoder.allMimeTypes());
		this.view.setModelKeys(new HashSet<>(Arrays.asList("foo1", "foo2")));
		this.model.addAttribute("foo1", "bar1");
		this.model.addAttribute("foo2", "bar2");

		try {
			doRender();
			fail();
		}
		catch (IllegalStateException ex) {
			String message = ex.getMessage();
			assertTrue(message, message.contains("Map rendering is not supported"));
		}
	}
