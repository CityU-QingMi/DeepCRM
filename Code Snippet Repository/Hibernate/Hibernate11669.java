   private Metadata buildMetadata(StandardServiceRegistry registry) {
      MetadataSources metadataSources = new MetadataSources( registry );
      for ( Class entityClass : getAnnotatedClasses() ) {
         metadataSources.addAnnotatedClass( entityClass );
      }

      Metadata metadata = metadataSources.buildMetadata();

      for ( PersistentClass entityBinding : metadata.getEntityBindings() ) {
         if (!entityBinding.isInherited()) {
            ( (RootClass) entityBinding ).setCacheConcurrencyStrategy( accessType.getExternalName() );
         }
      }

      // Collections don't have integrated version, these piggyback on parent's owner version (for DB).
      // However, this version number isn't extractable and is not passed to cache methods.
      AccessType collectionAccessType = accessType == AccessType.NONSTRICT_READ_WRITE ? AccessType.READ_WRITE : accessType;
      for ( Collection collectionBinding : metadata.getCollectionBindings() ) {
         collectionBinding.setCacheConcurrencyStrategy( collectionAccessType.getExternalName() );
      }

      return metadata;
   }
