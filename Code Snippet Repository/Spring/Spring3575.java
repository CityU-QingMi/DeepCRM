	@Test
	public void messageSourceResolvable() {
		// first code valid
		String[] codes1 = new String[] {"message.format.example3", "message.format.example2"};
		MessageSourceResolvable resolvable1 = new DefaultMessageSourceResolvable(codes1, null, "default");
		assertTrue("correct message retrieved", MSG_TXT3_US.equals(sac.getMessage(resolvable1, Locale.US)));

		// only second code valid
		String[] codes2 = new String[] {"message.format.example99", "message.format.example2"};
		MessageSourceResolvable resolvable2 = new DefaultMessageSourceResolvable(codes2, null, "default");
		assertTrue("correct message retrieved", MSG_TXT2_US.equals(sac.getMessage(resolvable2, Locale.US)));

		// no code valid, but default given
		String[] codes3 = new String[] {"message.format.example99", "message.format.example98"};
		MessageSourceResolvable resolvable3 = new DefaultMessageSourceResolvable(codes3, null, "default");
		assertTrue("correct message retrieved", "default".equals(sac.getMessage(resolvable3, Locale.US)));

		// no code valid, no default
		String[] codes4 = new String[] {"message.format.example99", "message.format.example98"};
		MessageSourceResolvable resolvable4 = new DefaultMessageSourceResolvable(codes4);

		exception.expect(NoSuchMessageException.class);
		sac.getMessage(resolvable4, Locale.US);
	}
