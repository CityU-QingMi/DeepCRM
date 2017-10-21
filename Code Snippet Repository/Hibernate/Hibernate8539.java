	@Test
	public void testRemoveFromIdbag() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Baz baz = new Baz();
		baz.setByteBag( new ArrayList() );
		byte[] bytes = { 12, 13 };
		baz.getByteBag().add( new byte[] { 10, 45 } );
		baz.getByteBag().add(bytes);
		baz.getByteBag().add( new byte[] { 1, 11 } );
		baz.getByteBag().add( new byte[] { 12 } );
		s.save(baz);
		s.flush();
		baz.getByteBag().remove(bytes);
		s.flush();
		baz.getByteBag().add(bytes);
		s.flush();
		s.delete(baz);
		s.getTransaction().commit();
		s.close();
	}
