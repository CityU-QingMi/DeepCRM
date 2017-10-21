	@Test
	public void nestedMessageSourceWithParamInChild() {
		StaticMessageSource source = new StaticMessageSource();
		StaticMessageSource parent = new StaticMessageSource();
		source.setParentMessageSource(parent);

		source.addMessage("param", Locale.ENGLISH, "value");
		parent.addMessage("with.param", Locale.ENGLISH, "put {0} here");

		MessageSourceResolvable resolvable = new DefaultMessageSourceResolvable(
				new String[] {"with.param"}, new Object[] {new DefaultMessageSourceResolvable("param")});

		assertEquals("put value here", source.getMessage(resolvable, Locale.ENGLISH));
	}
