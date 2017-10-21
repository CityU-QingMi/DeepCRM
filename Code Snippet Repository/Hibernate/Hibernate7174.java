    @Test
    public void test() {
        doInHibernate( this::sessionFactory, s -> {
            Child loadedChild = s.load( Child.class, lastChildID );
            checkDirtyTracking( loadedChild );

            loadedChild.name = "Barrabas";
            checkDirtyTracking( loadedChild, "name" );

            Parent loadedParent = loadedChild.parent;
            checkDirtyTracking( loadedChild, "name" );
            checkDirtyTracking( loadedParent );

            List<Child> loadedChildren = new ArrayList<>( loadedParent.children );
            loadedChildren.remove( 0 );
            loadedChildren.remove( loadedChild );
            loadedParent.setChildren( loadedChildren );

            Assert.assertNull( loadedChild.parent );
        } );
    }
