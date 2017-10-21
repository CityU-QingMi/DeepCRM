    protected boolean verifyAction(String className, String name, Location loc) {
        if (className.contains("{")) {
            LOG.debug("Action class [{}] contains a wildcard replacement value, so it can't be verified", className);
            return true;
        }
        try {
            if (objectFactory.isNoArgConstructorRequired()) {
                Class clazz = objectFactory.getClassInstance(className);
                if (!Modifier.isPublic(clazz.getModifiers())) {
                    throw new ConfigurationException("Action class [" + className + "] is not public", loc);
                }
                clazz.getConstructor(new Class[]{});
            }
        } catch (ClassNotFoundException e) {
            LOG.debug("Class not found for action [{}]", className, e);
            throw new ConfigurationException("Action class [" + className + "] not found", loc);
        } catch (NoSuchMethodException e) {
            LOG.debug("No constructor found for action [{}]", className, e);
            throw new ConfigurationException("Action class [" + className + "] does not have a public no-arg constructor", e, loc);
        } catch (RuntimeException ex) {
            // Probably not a big deal, like request or session-scoped Spring beans that need a real request
            LOG.info("Unable to verify action class [{}] exists at initialization", className);
            LOG.debug("Action verification cause", ex);
        } catch (Exception ex) {
            // Default to failing fast
            LOG.debug("Unable to verify action class [{}]", className, ex);
            throw new ConfigurationException(ex, loc);
        }
        return true;
    }
