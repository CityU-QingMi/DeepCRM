    public void testSerialization() throws Exception {
        Object targetObject = getTargetObject();
        
        for (int i = 0; i < getVersions().length; i++) {
            String version = getVersions()[i];
            
            verifyMatch(
                targetObject,
                deserialize(version, targetObject.getClass()));
        }
    }
