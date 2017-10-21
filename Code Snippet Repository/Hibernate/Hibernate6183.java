        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if("commit".equals( method.getName() )) {
                if ( transactionFailureTrigger.get() ) {
                    throw new PersistenceException( COMMIT_FAILURE );
                }
            }
            else if("rollback".equals( method.getName() )) {
                if ( transactionFailureTrigger.get() ) {
                    transactionFailureTrigger.set( false );
                    throw new PersistenceException( "Rollback failed!" );
                }
            }
            return method.invoke(delegate, args);
        }
