	public static void main(String[] argv) {
		Map<String, String> configurationOverrides = new HashMap<String, String>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory( "ConsolePU", configurationOverrides );
		EntityManager entityManager = emf.createEntityManager();

		populateTestData( entityManager );

		entityManager.getTransaction().begin();

		Session sesion = (Session) entityManager.getDelegate();
		System.out.println(
				sesion.createQuery(
						"select e from org.hibernate.envers.demo.Person_versions e " +
								"where " +
								"e.originalId._revision.id =" +
								"(select max(e2.originalId._revision.id) " +
								"from org.hibernate.envers.demo.Person_versions e2 " +
								"where e.originalId.id = :p0) "
				)
						.setParameter( "p0", 1 )
						.list()
		);

		entityManager.getTransaction().commit();

		entityManager.close();
		emf.close();
	}
