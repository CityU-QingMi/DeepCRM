	@Test
	@TestForIssue( jiraKey = "")
	public void testPrivateFieldSetter() throws Exception {
		AnEntity entity = new AnEntity( new PK( 1L ) );

		final String propertyName = "pk";
		final Getter getter = new GetterFieldImpl(
				AnEntity.class,
				propertyName,
				ReflectHelper.findField( AnEntity.class, propertyName)
		);
		final Setter setter = new SetterFieldImpl(
				AnEntity.class,
				propertyName,
				ReflectHelper.findField( AnEntity.class, propertyName)
		);

		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject( setter );

		final ObjectInputStream ois = new ObjectInputStream( new ByteArrayInputStream( baos.toByteArray() ) );

		final Setter setterClone = (Setter) ois.readObject();
		final PK pkNew = new PK( 2L );
		setterClone.set( entity, pkNew, null  );

		assertSame( pkNew, getter.get( entity ) );
	}
