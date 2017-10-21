	private void generateDetailLines(QuerySpace querySpace, int depth, PrintWriter printWriter) {
		printWriter.println(
				TreePrinterHelper.INSTANCE.generateNodePrefix( depth )
						+ querySpace.getUid() + " -> " + extractDetails( querySpace )
		);
		printWriter.println(
				TreePrinterHelper.INSTANCE.generateNodePrefix( depth+3 )
						+ "SQL table alias mapping - " + resolveSqlTableAliasFromQuerySpaceUid( querySpace.getUid() )
		);

		final EntityReferenceAliases entityAliases = resolveEntityReferenceAliases( querySpace.getUid() );
		final CollectionReferenceAliases collectionReferenceAliases = resolveCollectionReferenceAliases( querySpace.getUid() );

		if ( entityAliases != null ) {
			printWriter.println(
					TreePrinterHelper.INSTANCE.generateNodePrefix( depth+3 )
							+ "alias suffix - " + entityAliases.getColumnAliases().getSuffix()
			);
			printWriter.println(
					TreePrinterHelper.INSTANCE.generateNodePrefix( depth+3 )
							+ "suffixed key columns - "
							+ StringHelper.join( ", ", entityAliases.getColumnAliases().getSuffixedKeyAliases() )
			);
		}

		if ( collectionReferenceAliases != null ) {
			printWriter.println(
					TreePrinterHelper.INSTANCE.generateNodePrefix( depth+3 )
							+ "alias suffix - " + collectionReferenceAliases.getCollectionColumnAliases().getSuffix()
			);
			printWriter.println(
					TreePrinterHelper.INSTANCE.generateNodePrefix( depth+3 )
							+ "suffixed key columns - "
							+ StringHelper.join( ", ", collectionReferenceAliases.getCollectionColumnAliases().getSuffixedKeyAliases() )
			);
			final EntityReferenceAliases elementAliases = collectionReferenceAliases.getEntityElementAliases();
			if ( elementAliases != null ) {
				printWriter.println(
						TreePrinterHelper.INSTANCE.generateNodePrefix( depth+3 )
								+ "entity-element alias suffix - " + elementAliases.getColumnAliases().getSuffix()
				);
				printWriter.println(
						TreePrinterHelper.INSTANCE.generateNodePrefix( depth+3 )
								+ elementAliases.getColumnAliases().getSuffix()
								+ "entity-element suffixed key columns - "
								+ StringHelper.join( ", ", elementAliases.getColumnAliases().getSuffixedKeyAliases() )
				);
			}
		}
	}
