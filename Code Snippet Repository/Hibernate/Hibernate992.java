		private Iterator<ClassLoader> newClassLoaderIterator() {
			final ClassLoader threadClassLoader = locateTCCL();
			if ( tcclLookupPrecedence == TcclLookupPrecedence.NEVER || threadClassLoader == null ) {
				return newTcclNeverIterator();
			}
			else if ( tcclLookupPrecedence == TcclLookupPrecedence.AFTER ) {
				return newTcclAfterIterator(threadClassLoader);
			}
			else if ( tcclLookupPrecedence == TcclLookupPrecedence.BEFORE ) {
				return newTcclBeforeIterator(threadClassLoader);
			}
			else {
				throw new RuntimeException( "Unknown precedence: "+tcclLookupPrecedence );
			}
		}
