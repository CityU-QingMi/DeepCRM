    @Test
	@TestForIssue( jiraKey = "" )
    public void testCriteriaRestrictionOnIdManyToOne() {
        Session s = openSession();
        s.beginTransaction();

        s.createQuery( "from Course c join c.students cs join cs.student s where s.name = 'Foo'" ).list();

        Criteria criteria = s.createCriteria( Course.class );
        criteria.createCriteria( "students" ).createCriteria( "student" ).add( Restrictions.eq( "name", "Foo" ) );
        criteria.list();

        Criteria criteria2 = s.createCriteria( Course.class );
        criteria2.createAlias( "students", "cs" );
        criteria2.add( Restrictions.eq( "cs.value", "Bar" ) );
        criteria2.createAlias( "cs.student", "s" );
        criteria2.add( Restrictions.eq( "s.name", "Foo" ) );
        criteria2.list();

        s.getTransaction().commit();
        s.close();
    }
