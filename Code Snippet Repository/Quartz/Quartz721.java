        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ("commit".equals(method.getName())) {
                preCommitFailure();
                method.invoke(delegate, args);
                postCommitFailure();
                return null;
            } else {
                return method.invoke(delegate, args);
            }
        }
