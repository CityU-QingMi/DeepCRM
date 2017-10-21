    protected void addIfMatching(Test test, String fqn) {
        try {
            ClassLoader loader = getClassLoader();
            if (test.doesMatchClass()) {
                String externalName = fqn.substring(0, fqn.indexOf('.')).replace('/', '.');
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Checking to see if class " + externalName + " matches criteria [" + test + "]");
                }
    
                Class type = loader.loadClass(externalName);
                if (test.matches(type) ) {
                    classMatches.add( (Class<T>) type);
                }
            }
            if (test.doesMatchResource()) {
                URL url = loader.getResource(fqn);
                if (url == null) {
                    url = loader.getResource(fqn.substring(1));
                }
                if (url != null && test.matches(url)) {
                    resourceMatches.add(url);
                }
            }
        }
        catch (Throwable t) {
            if (LOG.isWarnEnabled()) {
        	LOG.warn("Could not examine class '" + fqn + "' due to a " +
                     t.getClass().getName() + " with message: " + t.getMessage());
            }
        }
    }
