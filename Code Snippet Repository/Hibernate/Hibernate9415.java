    	@Override
        protected Enumeration<URL> findResources(String name) throws IOException {
    		if (name.equals( "META-INF/services/org.hibernate.integrator.spi.Integrator" )) {
    			final URL serviceUrl = ConfigHelper.findAsResource(
    					"org/hibernate/test/service/org.hibernate.integrator.spi.Integrator" );
    			return new Enumeration<URL>() {
        			boolean hasMore = true;
        			
    				@Override
    				public boolean hasMoreElements() {
    					return hasMore;
    				}

    				@Override
    				public URL nextElement() {
    					hasMore = false;
    					return serviceUrl;
    				}
    			};
    		}
    		else {
    			return java.util.Collections.enumeration( java.util.Collections.<URL>emptyList() );
    		}
        }
