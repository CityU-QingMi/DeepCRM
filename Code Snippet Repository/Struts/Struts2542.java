        public <T> T getInstance(Class<T> type, String name) {
            if (type == ResultMapBuilder.class)
                return (T) resultMapBuilder;
            else if (type == InterceptorMapBuilder.class)
                return (T) interceptorMapBuilder;
            else if (type == ActionNameBuilder.class)
                return (T) actionNameBuilder;
            else if (type == ConventionsService.class)
                return (T) conventionsService;
            else if (type == FileManagerFactory.class)
                return (T) fileManagerFactory;
            return null;
        }
