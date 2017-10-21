    public void testGetValidatorsForGivenMethodNameWithoutReloading() throws ValidationException {
        FileManager fileManager = container.getInstance(FileManagerFactory.class).getFileManager();
        List validatorList = annotationActionValidatorManager.getValidators(SimpleAnnotationAction.class, alias, "execute");

        //disable configuration reload/devmode
        fileManager.setReloadingConfigs(false);

        //17 in the class level + 0 in the alias
        assertEquals(12, validatorList.size());
        
        validatorList = annotationActionValidatorManager.getValidators(SimpleAnnotationAction.class, alias, "execute");

        //expect same number of validators
        assertEquals(12, validatorList.size());
    }
