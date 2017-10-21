	@Test
	@TestForIssue(jiraKey = "")
	public void testSubQueryConstraintPropertyInEntityTable() {

		Session s = openSession();
		try {
			s.getTransaction().begin();
			// employee.employeeNumber is in Employee table
			String queryHQL = "from InvestmentCompany investmentCompany "
					+ "where exists "
					+ "(select employee "
					+ "from investmentCompany.employees as employee "
					+ "  where employee.employeeNumber = 666 )";
			s.createQuery( queryHQL ).uniqueResult();
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
