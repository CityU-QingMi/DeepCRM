	@Test
	public void servletVersion() {
		assertEquals(3, sc.getMajorVersion());
		assertEquals(1, sc.getMinorVersion());
		assertEquals(3, sc.getEffectiveMajorVersion());
		assertEquals(1, sc.getEffectiveMinorVersion());

		sc.setMajorVersion(4);
		sc.setMinorVersion(0);
		sc.setEffectiveMajorVersion(4);
		sc.setEffectiveMinorVersion(0);
		assertEquals(4, sc.getMajorVersion());
		assertEquals(0, sc.getMinorVersion());
		assertEquals(4, sc.getEffectiveMajorVersion());
		assertEquals(0, sc.getEffectiveMinorVersion());
	}
