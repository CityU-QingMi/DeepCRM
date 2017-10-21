        @Override
        public void addListener(String className)
        {
            if (!_enabled)
                throw new UnsupportedOperationException();

            try
            {
                @SuppressWarnings({ "unchecked", "rawtypes" })
                Class<? extends EventListener> clazz = _classLoader==null?Loader.loadClass(className):(Class)_classLoader.loadClass(className);
                addListener(clazz);
            }
            catch (ClassNotFoundException e)
            {
                throw new IllegalArgumentException(e);
            }
        }
