	@Override
	protected void afterMetadataBuilt(Metadata metadata) {
		if (addVersions) {
			for (PersistentClass clazz : metadata.getEntityBindings()) {
				if (clazz.getVersion() != null) {
					continue;
				}
				try {
					clazz.getMappedClass().getMethod("getVersion");
					clazz.getMappedClass().getMethod("setVersion", long.class);
				} catch (NoSuchMethodException e) {
					continue;
				}
				RootClass rootClazz = clazz.getRootClass();
				Property versionProperty = new Property();
				versionProperty.setName("version");
				SimpleValue value = new SimpleValue((MetadataImplementor) metadata, rootClazz.getTable());
				value.setTypeName("long");
				Column column = new Column();
				column.setValue(value);
				column.setName("version");
				value.addColumn(column);
				rootClazz.getTable().addColumn(column);
				versionProperty.setValue(value);
				rootClazz.setVersion(versionProperty);
				rootClazz.addProperty(versionProperty);
			}
		}
	}
