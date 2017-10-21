	@Test
	public void testBindExceptionSerializable() throws Exception {
		SerializablePerson tb = new SerializablePerson();
		tb.setName("myName");
		tb.setAge(99);

		BindException ex = new BindException(tb, "tb");
		ex.reject("invalid", "someMessage");
		ex.rejectValue("age", "invalidField", "someMessage");

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(ex);
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bais);

		BindException ex2 = (BindException) ois.readObject();
		assertTrue(ex2.hasGlobalErrors());
		assertEquals("invalid", ex2.getGlobalError().getCode());
		assertTrue(ex2.hasFieldErrors("age"));
		assertEquals("invalidField", ex2.getFieldError("age").getCode());
		assertEquals(new Integer(99), ex2.getFieldValue("age"));

		ex2.rejectValue("name", "invalidField", "someMessage");
		assertTrue(ex2.hasFieldErrors("name"));
		assertEquals("invalidField", ex2.getFieldError("name").getCode());
		assertEquals("myName", ex2.getFieldValue("name"));
	}
