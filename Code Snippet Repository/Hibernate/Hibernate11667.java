   private Field getField(Class<?> clazz, String fieldName) {
      Field f = null;
      while (clazz != null && clazz != Object.class) {
         try {
            f = clazz.getDeclaredField(fieldName);
            break;
         } catch (NoSuchFieldException e) {
            clazz = clazz.getSuperclass();
         }
      }
      if (f != null) {
         f.setAccessible(true);
      }
      return f;
   }
