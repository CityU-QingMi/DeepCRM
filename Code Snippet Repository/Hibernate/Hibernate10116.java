		public void generateData(SessionImplementor sessionImplementor, Map<String, Object> data) {
			// If the revision type is "DEL", it means that the object is removed from the collection. Then the
			// new owner will in fact be null.
			rd.getFakeBidirectionalRelationMapper().mapToMapFromEntity(
					sessionImplementor, data,
					revisionType == RevisionType.DEL ? null : owningEntity, null
			);
			rd.getFakeBidirectionalRelationMapper().mapModifiedFlagsToMapFromEntity(
					sessionImplementor, data,
					revisionType == RevisionType.DEL ? null : owningEntity, null
			);

			// Also mapping the index, if the collection is indexed.
			if ( rd.getFakeBidirectionalRelationIndexMapper() != null ) {
				rd.getFakeBidirectionalRelationIndexMapper().mapToMapFromEntity(
						sessionImplementor, data,
						revisionType == RevisionType.DEL ? null : index, null
				);
				rd.getFakeBidirectionalRelationIndexMapper().mapModifiedFlagsToMapFromEntity(
						sessionImplementor, data,
						revisionType == RevisionType.DEL ? null : index, null
				);
			}
		}
