		private TransactionManagerAdapter() throws HibernateException {
			try {
				synchronizationCallbackClass = Class.forName( "com.ibm.websphere.jtaextensions.SynchronizationCallback" );
				Class extendedJTATransactionClass = Class.forName( "com.ibm.websphere.jtaextensions.ExtendedJTATransaction" );
				registerSynchronizationMethod = extendedJTATransactionClass.getMethod(
						"registerSynchronizationCallbackForCurrentTran",
						new Class[] { synchronizationCallbackClass }
				);
				getLocalIdMethod = extendedJTATransactionClass.getMethod( "getLocalId", (Class[]) null );
			}
			catch ( ClassNotFoundException cnfe ) {
				throw new HibernateException( cnfe );
			}
			catch ( NoSuchMethodException nsme ) {
				throw new HibernateException( nsme );
			}
		}
