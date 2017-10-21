	public static void bind(Context ctx, String name, Object val) throws NamingException {
		try {
			ctx.rebind(name, val);
		}
		catch (Exception e) {
			Name n = ctx.getNameParser( "" ).parse( name );
			while ( n.size() > 1 ) {
				final String ctxName = n.get( 0 );

				Context subctx = null;
				try {
					subctx = (Context) ctx.lookup( ctxName );
				}
				catch (NameNotFoundException ignore) {
				}

				if ( subctx != null ) {
					ctx = subctx;
				}
				else {
					ctx = ctx.createSubcontext( ctxName );
				}
				n = n.getSuffix( 1 );
			}
			ctx.rebind( n, val );
		}
	}
