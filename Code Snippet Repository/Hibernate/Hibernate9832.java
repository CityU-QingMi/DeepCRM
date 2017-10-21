	private void addSyntheticIndexProperty(List value, String propertyAccessorName, ClassAuditingData classAuditingData) {
		final Value indexValue = value.getIndex();
		if ( indexValue != null && indexValue.getColumnIterator().hasNext() ) {
			final String indexColumnName = indexValue.getColumnIterator().next().getText();
			if ( indexColumnName != null ) {
				final PropertyAuditingData auditingData = new PropertyAuditingData(
						indexColumnName,
						propertyAccessorName,
						ModificationStore.FULL,
						RelationTargetAuditMode.AUDITED,
						null,
						null,
						false,
						true,
						indexValue
				);
				classAuditingData.addPropertyAuditingData( indexColumnName, auditingData );
			}
		}
	}
