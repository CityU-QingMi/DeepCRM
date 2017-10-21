   void setContextClassLoader(final ClassLoader classLoader) {
      PrivilegedAction<Void> action = new PrivilegedAction<Void>() {
         @Override
         public Void run() {
            Thread.currentThread().setContextClassLoader(classLoader);
            return null;
         }
      };
      AccessController.doPrivileged(action);
   }
