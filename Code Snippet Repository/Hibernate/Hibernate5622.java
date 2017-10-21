	@Test
	public void testConstructor() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		CriteriaBuilderImpl cb = (CriteriaBuilderImpl) em.getCriteriaBuilder();
		MetamodelImpl mm = (MetamodelImpl) em.getMetamodel();

        CriteriaQuery<Customer> cquery = cb.createQuery(Customer.class);
		Root<Customer> customer = cquery.from(Customer.class);
		EntityType<Customer> Customer_ = customer.getModel();

		cquery.select(
				cb.construct(
						Customer.class,
						customer.get(Customer_.getSingularAttribute("id", String.class)),
						customer.get(Customer_.getSingularAttribute("name", String.class))
				)
		);
		TypedQuery<Customer> tq = em.createQuery(cquery);
		tq.getResultList();

		em.getTransaction().commit();
		em.close();
	}
