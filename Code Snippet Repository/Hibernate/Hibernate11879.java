		public String render(Type firstArgumentType, final List args, final SessionFactoryImplementor factory) {

			if ( args.size() < 2 ) {
				throw new QueryException(
						"Spatial relate functions require at least two arguments"
				);
			}

			return isOGCStrict ?
					sdo.getOGCSpatialRelateSQL(
							(String) args.get( 0 ),
							(String) args.get( 1 ), this.relation
					) :
					sdo.getNativeSpatialRelateSQL(
							(String) args.get( 0 ),
							(String) args.get( 1 ), this.relation
					);
		}
