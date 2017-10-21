	@After
	public void tearDown(){
		EntityManager em = getOrCreateEntityManager();
		try {
			em.getTransaction().begin();
			em.createQuery( "delete from Game" ).executeUpdate();
			em.getTransaction().commit();
		}catch (Exception e){
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
			throw e;
		}finally {
			em.close();
		}
	}
