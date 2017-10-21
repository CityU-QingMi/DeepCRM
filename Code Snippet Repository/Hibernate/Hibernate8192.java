	@Test
	@TestForIssue(jiraKey = "")
	public void testSubQueryConstraintPropertyInSuperclassTable() {

		Session s = openSession();
		try {
			s.getTransaction().begin();
			// employee.firstName is in Person table (not Employee)
			String queryHQL = "from InvestmentCompany investmentCompany "
					+ "where exists "
					+ "(select employee "
					+ "from investmentCompany.employees as employee "
					+ "  where employee.firstName = 'Joe')";
			s.createQuery( queryHQL ).uniqueResult();
			s.getTransaction().commit();
		}
		catch (Exception e) {
			if ( s.getTransaction() != null && s.getTransaction().isActive() ) {
				s.getTransaction().rollback();
			}
			throw e;
		}
		finally {
			s.close();
		}
	}
