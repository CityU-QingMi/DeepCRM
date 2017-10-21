	@Test
	@TestForIssue(jiraKey = "")
	public void testTypedValueSerialization() throws Exception {
		final Type mockType = mock(Type.class);
		final String value = "foo";
		final TypedValue typedValue = new TypedValue(mockType, value);
		
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(typedValue);
        
        final ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        final TypedValue typedValueClone = (TypedValue) ois.readObject();
        
        assertEquals(typedValue.hashCode(), typedValueClone.hashCode());
        assertEquals(typedValue.toString(), typedValueClone.toString());
        assertEquals(typedValue.getValue(), typedValueClone.getValue());
	}
