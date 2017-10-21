    public String execute() {
        
        // set action error message if there was login error
        if(getError() != null) {
            if (authMethod == AuthMethod.OPENID) {
                addError("error.unmatched.openid");
            } else {
                addError("error.password.mismatch");
            }
        }
        
        return SUCCESS;
    }
