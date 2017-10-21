	@Test
	public void testImmutableAttribute(){
		configuration().addAttributeConverter( ExifConverter.class);
		configuration().addAttributeConverter( CaptionConverter.class);
		Session s = openSession();
		Transaction tx = s.beginTransaction();

		Photo photo = new Photo();
		photo.setName( "cat.jpg");
		photo.setMetadata( new Exif(Collections.singletonMap( "fake", "first value")));
		photo.setCaption( new Caption( "Cat.jpg caption" ) );
		s.persist(photo);
		tx.commit();
		s.close();

		// try changing the attribute
		s = openSession();
		tx = s.beginTransaction();

		Photo cat = s.get(Photo.class, photo.getId());
		assertNotNull(cat);
		cat.getMetadata().getAttributes().put( "fake", "second value");
		cat.getCaption().setText( "new caption" );

		tx.commit();
		s.close();

		// retrieving the attribute again - it should be unmodified since object identity is the same
		s = openSession();
		tx = s.beginTransaction();

		cat = s.get(Photo.class, photo.getId());
		assertNotNull(cat);
		assertEquals("Metadata should not have changed", "first value", cat.getMetadata().getAttribute( "fake"));
		assertEquals("Caption should not have changed", "Cat.jpg caption", cat.getCaption().getText());

		tx.commit();
		s.close();
	}
