    public ClassLoader getClassLoader() {
        // To follow the same behavior of Class.forName(...) I had to play
        // dirty (Supported by Sun, IBM & BEA JVMs)
        try {
            // Get a reference to this class' class-loader
            ClassLoader cl = this.getClass().getClassLoader();
            // Create a method instance representing the protected
            // getCallerClassLoader method of class ClassLoader
            Method mthd = ClassLoader.class.getDeclaredMethod(
                    "getCallerClassLoader", new Class<?>[0]);
            // Make the method accessible.
            AccessibleObject.setAccessible(new AccessibleObject[] {mthd}, true);
            // Try to get the caller's class-loader
            return (ClassLoader)mthd.invoke(cl, new Object[0]);
        } catch (Throwable all) {
            // Use this class' class-loader
            return this.getClass().getClassLoader();
        }
    }
