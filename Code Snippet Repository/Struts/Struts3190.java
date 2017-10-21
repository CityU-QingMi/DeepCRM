    public TagHandler create(TagHandler next) {
        try {
            TagHandler th = (TagHandler) tagHandlerClass.newInstance();
            th.setNext(next);
            return th;
        } catch (Exception e) {
            LOG.error("Failed to instantiate tag handler class [{}]", tagHandlerClass.getName(), e);
        }
        
        return null;
    }
