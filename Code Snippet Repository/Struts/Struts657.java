    protected MultiPartRequest getMultiPartRequest() {
        MultiPartRequest mpr = null;
        //check for alternate implementations of MultiPartRequest
        Set<String> multiNames = getContainer().getInstanceNames(MultiPartRequest.class);
        for (String multiName : multiNames) {
            if (multiName.equals(multipartHandlerName)) {
                mpr = getContainer().getInstance(MultiPartRequest.class, multiName);
            }
        }
        if (mpr == null ) {
            mpr = getContainer().getInstance(MultiPartRequest.class);
        }
        return mpr;
    }
