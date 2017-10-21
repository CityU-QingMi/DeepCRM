    @Test
    @TestForIssue( jiraKey = "" )
    public void testUnaryMinus(){
        Session s = openSession();
        s.beginTransaction();
        Human stliu = new Human();
        stliu.setIntValue( 26 );

        s.persist( stliu );
        s.getTransaction().commit();
        s.clear();
        s.beginTransaction();
        List list =s.createQuery( "from Human h where -(h.intValue - 100)=74" ).list();
        assertEquals( 1, list.size() );
        s.getTransaction().commit();
        s.close();


    }
