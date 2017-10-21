        public Class get() throws ClassNotFoundException {
            if (clazz != null) return clazz;
            if (notFound != null) throw notFound;
            try {
                this.clazz = classFinder.getClassLoaderInterface().loadClass(name);
                return clazz;
            } catch (ClassNotFoundException notFound) {
                classFinder.getClassesNotLoaded().add(name);
                this.notFound = notFound;
                throw notFound;
            }
        }
