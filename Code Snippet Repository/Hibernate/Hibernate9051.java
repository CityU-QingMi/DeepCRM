	@Test
	public void testIt() {
		DoesNotWorkPk pk = new DoesNotWorkPk();
		pk.setId1( "ZZZ" );
		pk.setId2( "00" );

//		{
//			Session session = openSession();
//			session.beginTransaction();
//			DoesNotWork entity = new DoesNotWork( pk );
//			entity.setGlobalNotes( Arrays.asList( "My first note!" ) );
//			session.save( entity );
//			session.getTransaction().commit();
//			session.close();
//		}

		{
			Session session = openSession();
			session.beginTransaction();
			DoesNotWork entity = (DoesNotWork) session.get( DoesNotWork.class, pk );
			assertNotNull( entity );
			List<String> notes = entity.getGlobalNotes();
			assertNotNull( notes );
			assertEquals( 2, notes.size() );
			for ( String s : notes ) {
				System.out.println( s );
			}
			session.delete( entity );
			session.getTransaction().commit();
			session.close();
		}
	}
