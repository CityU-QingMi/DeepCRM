      public ClassLoaderAwareListener(Object listener, ClassLoaderAwareCache cache) {
         this.listener = listener;
         this.cache = cache;
         for (Method method : listener.getClass().getMethods()) {
            for (Map.Entry<Class<? extends Annotation>, Event.Type> entry : events.entrySet()) {
               Class<? extends Annotation> annotation = entry.getKey();
               if (method.isAnnotationPresent(annotation)) {
                  List<Method> methods = this.methods.get(entry.getValue());
                  if (methods == null) {
                     methods = new LinkedList<Method>();
                     this.methods.put(entry.getValue(), methods);
                  }
                  methods.add(method);
               }
            }
         }
      }
