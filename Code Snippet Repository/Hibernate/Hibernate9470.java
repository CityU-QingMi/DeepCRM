		@Override
		public String[] sqlCreateStrings(Dialect dialect) {
			return new String[] {
					"create function all_items() return refcursor as \n" +
							"	'declare someCursor refcursor;\n" +
							"   begin\n" +
							"   	open someCursor for select * from ITEM;\n" +
							"       return someCursor;\n" +
							"   end;' language plpgsql;"
			};
		}
