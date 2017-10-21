    @SuppressWarnings( {""})
	private Object createData() {
        Seller stliu = new Seller();
        stliu.setId( createID( "stliu" ) );
        CarBuyer zd = new CarBuyer();
        zd.setId( createID( "zd" ) );
        zd.setSeller( stliu );
        zd.setSellerName( stliu.getId().getName() );
        stliu.getBuyers().add( zd );
        return stliu;
    }
