	@Test
	public void testTypedValue() {
		TypedValue tv1 = new TypedValue("hello");
		TypedValue tv2 = new TypedValue("hello");
		TypedValue tv3 = new TypedValue("bye");
		assertEquals(String.class, tv1.getTypeDescriptor().getType());
		assertEquals("TypedValue: 'hello' of [java.lang.String]", tv1.toString());
		assertEquals(tv1, tv2);
		assertEquals(tv2, tv1);
		assertNotEquals(tv1, tv3);
		assertNotEquals(tv2, tv3);
		assertNotEquals(tv3, tv1);
		assertNotEquals(tv3, tv2);
		assertEquals(tv1.hashCode(), tv2.hashCode());
		assertNotEquals(tv1.hashCode(), tv3.hashCode());
		assertNotEquals(tv2.hashCode(), tv3.hashCode());
	}
