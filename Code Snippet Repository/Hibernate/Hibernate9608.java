    @Test
    @TestForIssue(jiraKey = "")
    public void testNativeQuery() {
        Issue issue = new Issue();
        issue.setIssueNumber( "HHH-2304" );
        issue.setDescription( "Wrong type detection for sql type char(x) columns" );

        Session session = openSession();
        session.beginTransaction();
        session.persist( issue );
        session.getTransaction().commit();
        session.close();

        session = openSession(  );
        session.beginTransaction();
        Object issueNumber = session.createSQLQuery( "select issue.issueNumber from Issue issue" ).uniqueResult();
        session.getTransaction().commit();
        session.close();

        assertNotNull( issueNumber );
        assertTrue( issueNumber instanceof String );
        assertEquals( "HHH-2304", issueNumber );


    }
