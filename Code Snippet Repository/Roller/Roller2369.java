    public void myValidate() {

        //
        // TODO: don't allow upload if user is over quota
        //

        // make sure uploads are enabled
        if (!WebloggerRuntimeConfig.getBooleanProperty("uploads.enabled")) {
            addError("error.upload.disabled");
        }
    }
