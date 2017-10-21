     public static Iterator<URL> getResources(String resourceName, Class callingClass, boolean aggregate) throws IOException {

         AggregateIterator<URL> iterator = new AggregateIterator<>();

         iterator.addEnumeration(Thread.currentThread().getContextClassLoader().getResources(resourceName));

         if (!iterator.hasNext() || aggregate) {
             iterator.addEnumeration(ClassLoaderUtil.class.getClassLoader().getResources(resourceName));
         }

         if (!iterator.hasNext() || aggregate) {
             ClassLoader cl = callingClass.getClassLoader();

             if (cl != null) {
                 iterator.addEnumeration(cl.getResources(resourceName));
             }
         }

         if (!iterator.hasNext() && (resourceName != null) && ((resourceName.length() == 0) || (resourceName.charAt(0) != '/'))) { 
             return getResources('/' + resourceName, callingClass, aggregate);
         }

         return iterator;
     }
