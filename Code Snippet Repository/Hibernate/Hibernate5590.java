    @Test
	public void testDatasourceInjection() throws Exception {
		File current = new File(".");
		File sub = new File(current, "puroot");
		sub.mkdir();
		PersistenceUnitInfoImpl info = new PersistenceUnitInfoImpl( sub.toURI().toURL(), new String[]{} );
		try {
			emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory( info, null );
			try {
				emf.createEntityManager().createQuery( "select i from Item i" ).getResultList();
			}
			finally {
				try {
					emf.close();
				}
				catch (Exception ignore) {
					int i = 0;
				}
			}
			Assert.fail( "FakeDatasource should have been used" );
		}
		catch (PersistenceException pe) {
			if(emf != null){
				emf.close();
			}
			Assert.assertTrue( pe.getCause() instanceof FakeDataSourceException );
		}
		catch (FakeDataSourceException fde) {
			//success
		}
		finally {
			sub.delete();
		}
	}
