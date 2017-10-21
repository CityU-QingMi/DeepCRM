    @Before
    public void prepare() {
        doInHibernate( this::sessionFactory, s -> {
            Post post = new Post();

            Tag tag1 = new Tag( "tag1" );
            Tag tag2 = new Tag( "tag2" );

            Set<Tag> tagSet = new HashSet<>();
            tagSet.add( tag1 );
            tagSet.add( tag2 );
            post.tags = tagSet;

            AdditionalDetails details = new AdditionalDetails();
            details.post = post;
            details.details = "Some data";
            post.additionalDetails = details;

            postId = (Long) s.save( post );
        } );
    }
