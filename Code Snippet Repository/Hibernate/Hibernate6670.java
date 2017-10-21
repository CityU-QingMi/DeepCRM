	@Test
	public void testChangeImmutableAttribute(){
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

		// replacing the attribute
		s = openSession();
		tx = s.beginTransaction();

		Photo cat = s.get(Photo.class, photo.getId());
		assertNotNull(cat);
		cat.setMetadata( new Exif(Collections.singletonMap( "fake", "second value")));
		cat.setCaption( new Caption( "new caption" ) );

		tx.commit();
		s.close();

		// retrieving the attribute again - it should be modified since the holder object has changed as well
		s = openSession();
		tx = s.beginTransaction();

		cat = s.get(Photo.class, photo.getId());
		assertNotNull(cat);

		assertEquals("Metadata should have changed", "second value", cat.getMetadata().getAttribute( "fake"));
		assertEquals("Caption should have changed", "new caption", cat.getCaption().getText());

		tx.commit();
		s.close();
	}
