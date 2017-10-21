	@Test
	public void testByteArrayStringRepresentation() {
		Session s = openSession();
		s.getTransaction().begin();
		try {
			Image image = new Image();
			image.id = 1L;
			image.content = new byte[] {1, 2, 3};

			s.save( image );
			s.getTransaction().commit();
		}
		catch (Exception e) {
			if ( s.getTransaction() != null && s.getTransaction().getStatus() == TransactionStatus.ACTIVE ) {
				s.getTransaction().rollback();
			}
			fail( e.getMessage() );
		}
		finally {
			s.close();
		}

		s = openSession();
		s.getTransaction().begin();
		try {
			assertArrayEquals( new byte[] {1, 2, 3}, s.find( Image.class, 1L ).content );
			s.getTransaction().commit();
		}
		catch (Exception e) {
			if ( s.getTransaction() != null && s.getTransaction().getStatus() == TransactionStatus.ACTIVE ) {
				s.getTransaction().rollback();
			}
			fail( e.getMessage() );
		}
		finally {
			s.close();
		}
	}
