        public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            if (name.startsWith("org.xml.") || name.startsWith("org.w3c.")
                    || name.startsWith("java.") || name.startsWith("javax.")
                    || name.startsWith("sun.") || name.startsWith("com.sun.")) {
                return super.loadClass(name, resolve);
            }

            ClassLoader parent = getParent();
            // First, check if the class has already been loaded
            Class c = findLoadedClass(name);
            if (c == null) {
                try {
                    c = findClass(name);
                } catch (Throwable t) {
                    // If still not found, only then ask the parent
                    c = parent.loadClass(name);
                }
            }
            if (resolve) {
                resolveClass(c);
            }

            return c;
        }
