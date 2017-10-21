    @Test
    public void test() {
        doInHibernate( this::sessionFactory, s -> {
            Query query = s.createQuery( "from AdditionalDetails where id=" + postId );
            AdditionalDetails additionalDetails = (AdditionalDetails) query.getSingleResult();
            additionalDetails.details = "New data";
            s.persist( additionalDetails );

            // additionalDetais.post.tags get deleted on commit
        } );

        doInHibernate( this::sessionFactory, s -> {
            Query query = s.createQuery( "from Post where id=" + postId );
            Post retrievedPost = (Post) query.getSingleResult();

            assertFalse( "No tags found", retrievedPost.tags.isEmpty() );
            retrievedPost.tags.forEach( tag -> System.out.println( "Found tag: " + tag ) );
        } );
    }
