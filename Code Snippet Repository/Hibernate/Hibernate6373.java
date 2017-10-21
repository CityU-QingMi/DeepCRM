	private void createData(Session s){
        SomeEntity someEntity = new SomeEntity();
        someEntity.setId( new SomeEntityId( ) );
        someEntity.getId().setId( 1 );
        someEntity.getId().setVersion( 11 );
        someEntity.setProp( "aa" );
        s.persist( someEntity );
        
        someEntity = new SomeEntity();
        someEntity.setId( new SomeEntityId( ) );
        someEntity.getId().setId( 1 );
        someEntity.getId().setVersion( 12 );
        someEntity.setProp( "bb" );
        s.persist( someEntity );
        
        someEntity = new SomeEntity();
        someEntity.setId( new SomeEntityId( ) );
        someEntity.getId().setId( 10 );
        someEntity.getId().setVersion( 21 );
        someEntity.setProp( "cc1" );
        s.persist( someEntity );
        
        someEntity = new SomeEntity();
        someEntity.setId( new SomeEntityId( ) );
        someEntity.getId().setId( 10 );
        someEntity.getId().setVersion( 22 );
        someEntity.setProp( "cc2" );
        s.persist( someEntity );
        
        someEntity = new SomeEntity();
        someEntity.setId( new SomeEntityId( ) );
        someEntity.getId().setId( 10 );
        someEntity.getId().setVersion( 23 );
        someEntity.setProp( "cc3" );
        s.persist( someEntity );
	}
