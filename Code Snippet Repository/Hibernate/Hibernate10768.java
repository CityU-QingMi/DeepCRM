	@Test
	@Priority(10)
	public void initData() {
		EntityManager entityManager = getEntityManager();
		try {
			// Revision 1 - insertion
			Professor professor = new Professor();
			Student student = new Student();
			professor.getStudents().add( student );
			student.getProfessors().add( professor );
			entityManager.getTransaction().begin();
			entityManager.persist( professor );
			entityManager.persist( student );
			entityManager.getTransaction().commit();
			entityManager.clear();

			// Revision 2 - deletion
			entityManager.getTransaction().begin();
			professor = entityManager.find( Professor.class, professor.getId() );
			student = entityManager.find( Student.class, student.getId() );
			entityManager.remove( professor );
			entityManager.remove( student );
			// the issue is student.getProfessors() throws a LazyInitializationException.
			entityManager.getTransaction().commit();
		}
		finally {
			entityManager.close();
		}
	}
