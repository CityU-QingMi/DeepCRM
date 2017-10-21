	@Test
	public void testMatchOneMethod() throws Throwable {
		pc.addMethodName("echo");
		pc.addMethodName("set*");
		assertEquals(0, nop.getCount());
		proxied.getName();
		proxied.getName();
		assertEquals(0, nop.getCount());
		proxied.echo(null);
		assertEquals(1, nop.getCount());

		proxied.setName("");
		assertEquals(2, nop.getCount());
		proxied.setAge(25);
		assertEquals(25, proxied.getAge());
		assertEquals(3, nop.getCount());
	}
