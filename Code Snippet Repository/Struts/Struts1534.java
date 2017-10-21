    public void testBuildsValidatorsForClassError() {
        // for this test we need to have a file manager with reloadingConfigs to true
        container.getInstance(FileManagerFactory.class).getFileManager().setReloadingConfigs(true);
        // no validator found, but no check on file since it is not in cache
        actionValidatorManager.getValidators(List.class, null);
        // this second call will try reload a not existing file
        // and causes a NPE (see WW-3850)
        try {
            actionValidatorManager.getValidators(List.class, null);
        } catch (Exception e) {
            fail("Exception occurred " + e);
        }
    }
