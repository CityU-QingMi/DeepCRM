	@Test
	@TestForIssue( jiraKey = "" )
	public void testOneToManyEmbeddableBiDirectionalDotNotationInMappedBy() throws Exception {
		// Section 11.1.26
		// The ManyToOne annotation may be used within an embeddable class to specify a relationship from the embeddable
		// class to an entity class. If the relationship is bidirectional, the non-owning OneToMany entity side must use the
		// mappedBy element of the OneToMany annotation to specify the relationship field or property of the embeddable field
		// or property on the owning side of the relationship. The dot (".") notation syntax must be used in the mappedBy
		// element to indicate the relationship attribute within the embedded attribute. The value of each identifier used
		// with the dot notation is the name of the respective embedded field or property.
		Session s;
		s = openSession();
		s.getTransaction().begin();
		Employee e = new Employee();
		JobInfo job = new JobInfo();
		job.setJobDescription( "Sushi Chef" );
		ProgramManager pm = new ProgramManager();
		Collection<Employee> employees = new ArrayList<Employee>();
		employees.add(e);
		pm.setManages( employees );
		job.setPm(pm);
		e.setJobInfo( job );
		s.persist( e );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		e = (Employee) s.get( e.getClass(), e.getId() );
		assertEquals( "same job in both directions", 
			e.getJobInfo().getJobDescription(),
			e.getJobInfo().getPm().getManages().iterator().next().getJobInfo().getJobDescription()  );
		s.getTransaction().commit();
		s.close();
	}
