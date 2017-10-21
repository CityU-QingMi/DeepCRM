	@Test
	public void testSets() throws Throwable {
		pc.setMappedNames(new String[] { "set*", "echo" });
		assertEquals(0, nop.getCount());
		proxied.getName();
		proxied.setName("");
		assertEquals(1, nop.getCount());
		proxied.echo(null);
		assertEquals(2, nop.getCount());
	}
