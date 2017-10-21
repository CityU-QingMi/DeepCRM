    private void initializeProperties(ActionInvocation invocation) throws Exception {

        if (height != null) {
            height = conditionalParse(height, invocation);
        }

        if (width != null) {
            width = conditionalParse(width, invocation);
        }

        if (type != null) {
            type = conditionalParse(type, invocation);
        }
        
        if ( type == null) {
            type = DEFAULT_TYPE;
        }
    }
