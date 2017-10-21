	@Test
	@TestForIssue( jiraKey = "")
	public void testProtectedMethodSetter() throws Exception {
		final AnEntity entity = new AnEntity( new PK( 1L ) );

		final Getter getter = new GetterMethodImpl(
				AnEntity.class,
				"pk",
				ReflectHelper.findGetterMethod( AnEntity.class, "pk" )
		);
		final Setter setter = new SetterMethodImpl(
				AnEntity.class,
				"pk",
				ReflectHelper.findSetterMethod( AnEntity.class, "pk", PK.class )
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
