   private static Metadata buildMetadata(StandardServiceRegistry registry) {
      final String cacheStrategy = "transactional";

      MetadataSources metadataSources = new MetadataSources( registry );
      for ( Class entityClass : getAnnotatedClasses() ) {
         metadataSources.addAnnotatedClass( entityClass );
      }

      Metadata metadata = metadataSources.buildMetadata();

      for ( PersistentClass entityBinding : metadata.getEntityBindings() ) {
         if (!entityBinding.isInherited()) {
            ( (RootClass) entityBinding ).setCacheConcurrencyStrategy( cacheStrategy);
         }
      }

      for ( Collection collectionBinding : metadata.getCollectionBindings() ) {
         collectionBinding.setCacheConcurrencyStrategy( cacheStrategy );
      }

      return metadata;
   }
