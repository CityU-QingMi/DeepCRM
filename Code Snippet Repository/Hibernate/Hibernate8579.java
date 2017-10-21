	@Test
    @SkipForDialect( value = SybaseASE15Dialect.class, jiraKey = "" )
	public void testCompositeKeyPathExpressions() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		s.createQuery( "select fum1.fo from Fum fum1 where fum1.fo.fum is not null" ).list();
		s.createQuery( "from Fum fum1 where fum1.fo.fum is not null order by fum1.fo.fum" ).list();
		if ( !(getDialect() instanceof MySQLDialect) && !(getDialect() instanceof HSQLDialect) && !(getDialect() instanceof MckoiDialect) && !(getDialect() instanceof PointbaseDialect) ) {
			s.createQuery( "from Fum fum1 where exists elements(fum1.friends)" ).list();
			if(!(getDialect() instanceof TimesTenDialect)) { // can't execute because TimesTen can't do subqueries combined with aggreations
				s.createQuery( "from Fum fum1 where size(fum1.friends) = 0" ).list();
			}
		}
		s.createQuery( "select elements(fum1.friends) from Fum fum1" ).list();
		s.createQuery( "from Fum fum1, fr in elements( fum1.friends )" ).list();
		s.getTransaction().commit();
		s.close();
	}
