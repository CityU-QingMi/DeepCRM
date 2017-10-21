        @SuppressWarnings("")
        private Constructor<T> findConstructorIn(Class<T> implementation) {
            Constructor<T> found = null;
            Constructor<T>[] declaredConstructors = (Constructor<T>[]) implementation.getDeclaredConstructors();
            for (Constructor<T> constructor : declaredConstructors) {
                if (constructor.getAnnotation(Inject.class) != null) {
                    if (found != null) {
                        throw new DependencyException("More than one constructor annotated"
                                + " with @Inject found in " + implementation + ".");
                    }
                    found = constructor;
                }
            }
            if (found != null) {
                return found;
            }

            // If no annotated constructor is found, look for a no-arg constructor
            // instead.
            try {
                return implementation.getDeclaredConstructor();
            } catch (NoSuchMethodException e) {
                throw new DependencyException("Could not find a suitable constructor in " + implementation.getName() + ".");
            }
        }
