		@Override
		public void addSecondaryTable(LocalMetadataBuildingContext buildingContext, Identifier logicalName, Join secondaryTableJoin) {
			if ( Identifier.areEqual( primaryTableLogicalName, logicalName ) ) {
				throw new org.hibernate.boot.MappingException(
						String.format(
								Locale.ENGLISH,
								"Attempt to add secondary table with same name as primary table [%s]",
								primaryTableLogicalName
						),
						buildingContext.getOrigin()
				);
			}


			if ( secondaryTableJoinMap == null ) {
				//secondaryTableJoinMap = new HashMap<Identifier,Join>();
				//secondaryTableJoinMap.put( logicalName, secondaryTableJoin );
				secondaryTableJoinMap = new HashMap<String,Join>();
				secondaryTableJoinMap.put( logicalName.getCanonicalName(), secondaryTableJoin );
			}
			else {
				//final Join existing = secondaryTableJoinMap.put( logicalName, secondaryTableJoin );
				final Join existing = secondaryTableJoinMap.put( logicalName.getCanonicalName(), secondaryTableJoin );

				if ( existing != null ) {
					throw new org.hibernate.boot.MappingException(
							String.format(
									Locale.ENGLISH,
									"Added secondary table with same name [%s]",
									logicalName
							),
							buildingContext.getOrigin()
					);
				}
			}
		}
