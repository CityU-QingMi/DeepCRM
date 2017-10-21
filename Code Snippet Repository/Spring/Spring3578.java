	@Test
	public void nestedMessageSourceWithParamInParent() {
		StaticMessageSource source = new StaticMessageSource();
		StaticMessageSource parent = new StaticMessageSource();
		source.setParentMessageSource(parent);

		parent.addMessage("param", Locale.ENGLISH, "value");
		source.addMessage("with.param", Locale.ENGLISH, "put {0} here");

		MessageSourceResolvable resolvable = new DefaultMessageSourceResolvable(
				new String[] {"with.param"}, new Object[] {new DefaultMessageSourceResolvable("param")});

		assertEquals("put value here", source.getMessage(resolvable, Locale.ENGLISH));
	}
