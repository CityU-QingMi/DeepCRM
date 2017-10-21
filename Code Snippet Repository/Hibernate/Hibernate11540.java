   @Override
   protected void configureStandardServiceRegistryBuilder(StandardServiceRegistryBuilder ssrb) {
      // This applies to manually set LOCK_TIMEOUT for H2 DB. AvailableSettings.JPA_LOCK_TIMEOUT
      // works only for queries, not for CRUDs, so we have to modify the connection URL.
      // Alternative could be executing SET LOCK_TIMEOUT 100 as a native query.
      String url = (String) ssrb.getSettings().get(AvailableSettings.URL);
      if (url != null && url.contains("LOCK_TIMEOUT")) {
         url = url.replaceAll("LOCK_TIMEOUT=[^;]*", "LOCK_TIMEOUT=100");
      }
      ssrb.applySetting(AvailableSettings.URL, url);
   }
