	public void addProperty(Property prop, Ejb3Column[] columns, XClass declaringClass) {
		//Ejb3Column.checkPropertyConsistency( ); //already called earlier
/**/
/**/
/**/
/**/
/**/
		if (columns != null) {
			Table table = columns[0].getTable();
			if ( !table.equals( component.getTable() ) ) {
				if ( component.getPropertySpan() == 0 ) {
					component.setTable( table );
				}
				else {
					throw new AnnotationException(
							"A component cannot hold properties split into 2 different tables: "
									+ this.getPath()
					);
				}
			}
		}
		addProperty( prop, declaringClass );
	}
