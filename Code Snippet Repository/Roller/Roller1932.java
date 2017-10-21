    private void myValidate() {
        
        if(StringUtils.isEmpty(getBean().getTitle())) {
            addError("planetGroups.error.title");
        }
        
        if(StringUtils.isEmpty(getBean().getHandle())) {
            addError("planetGroups.error.handle");
        }
        
        if(getBean().getHandle() != null && "all".equals(getBean().getHandle())) {
            addError("planetGroups.error.nameReserved");
        }
        
        // make sure duplicate group handles are prevented
    }
