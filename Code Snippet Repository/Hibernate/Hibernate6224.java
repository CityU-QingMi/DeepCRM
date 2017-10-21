	@Test
	@TestForIssue( jiraKey = "")
	public void testPrivateFieldGetter() throws Exception {
		final AnEntity entity = new AnEntity( new PK( 1L ) );

		final String propertyName = "pk";
		final Getter getter = new GetterFieldImpl(
				AnEntity.class,
				propertyName,
				ReflectHelper.findField( AnEntity.class, propertyName)
		);
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject( getter );

		final ObjectInputStream ois = new ObjectInputStream( new ByteArrayInputStream( baos.toByteArray() ) );

		final Getter getterClone = (Getter) ois.readObject();

		assertSame( getter.get( entity ), getterClone.get( entity ) );
	}
