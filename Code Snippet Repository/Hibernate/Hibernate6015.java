    	@Override
        protected Enumeration<URL> findResources(String name) throws IOException {
    		if (name.equals( "META-INF/persistence.xml" )) {
    			final URL puUrl = ConfigHelper.findAsResource(
    					"org/hibernate/jpa/test/persistenceunit/META-INF/persistence.xml" );
    			return new Enumeration<URL>() {
        			boolean hasMore = true;
        			
    				@Override
    				public boolean hasMoreElements() {
    					return hasMore;
    				}

    				@Override
    				public URL nextElement() {
    					hasMore = false;
    					return puUrl;
    				}
    			};
    		}
    		else {
    			return java.util.Collections.emptyEnumeration();
    		}
        }
