    @Test
    @TestForIssue( jiraKey = "" )
    public void testAccess() {
        doInHibernate( this::sessionFactory, s -> {
            Child c1 = (Child) s.createQuery( "from Child c where c.name = :name" ).setParameter( "name", "steve" ).uniqueResult();

            // verify the expected initial loaded state
            assertLoaded( c1, "name" );
            assertNotLoaded( c1, "nickName" );
            assertNotLoaded( c1, "parent" );
            assertNotLoaded( c1, "alternateParent" );

            // Now lets access nickName which ought to initialize nickName and parent, but not alternateParent
            c1.getNickName();
            assertLoaded( c1, "nickName" );
            assertLoaded( c1, "parent" );
            assertNotLoaded( c1, "alternateParent" );
            assertEquals( "Hibernate", c1.parent.nombre );
            assertFalse( c1.parent instanceof HibernateProxy );

            Child c2 = (Child) s.createQuery( "from Child c where c.name = :name" ).setParameter( "name", "sally" ).uniqueResult();

            // verify the expected initial loaded state
            assertLoaded( c2, "name" );
            assertNotLoaded( c2, "nickName" );
            assertNotLoaded( c2, "parent" );
            assertNotLoaded( c2, "alternateParent" );

            // Now lets access alternateParent which ought to initialize alternateParent and nothing else
            c2.getAlternateParent();
            assertNotLoaded( c2, "nickName" );
            assertNotLoaded( c2, "parent" );
            assertLoaded( c2, "alternateParent" );
            assertEquals( "Hibernate", c2.alternateParent.nombre );
            assertFalse( c2.alternateParent instanceof HibernateProxy );
        } );
    }
