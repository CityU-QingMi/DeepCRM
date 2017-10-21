	@Test
	@TestForIssue( jiraKey = "")
	public void testProtectedMethodGetter() throws Exception {
		final AnEntity entity = new AnEntity( new PK( 1L ) );

		final Getter getter = new GetterMethodImpl(
				AnEntity.class,
				"pk",
				ReflectHelper.findGetterMethod( AnEntity.class, "pk" )
		);
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject( getter );

		final ObjectInputStream ois = new ObjectInputStream( new ByteArrayInputStream( baos.toByteArray() ) );

		final Getter getterClone = (Getter) ois.readObject();

		assertSame( getter.get( entity ), getterClone.get( entity ) );
	}
