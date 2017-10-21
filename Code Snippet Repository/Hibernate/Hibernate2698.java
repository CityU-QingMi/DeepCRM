	public void setScalarColumnText(int i) throws SemanticException {
		if (nakedPropertyRef) {
			// do *not* over-write the column text, as that has already been
			// "rendered" during resolve
			ColumnHelper.generateSingleScalarColumn(this, i);
		}
		else {
			FromElement fe = getFromElement();
			if (fe != null) {
				if ( fe.getQueryableCollection() != null && fe.getQueryableCollection().getElementType().isComponentType() ) {
					ColumnHelper.generateScalarColumns( this, getColumns(), i );
				}
				else {
					setText(fe.renderScalarIdentifierSelect(i));
				}
			}
			else {
				ColumnHelper.generateSingleScalarColumn(this, i);
			}
		}
	}
