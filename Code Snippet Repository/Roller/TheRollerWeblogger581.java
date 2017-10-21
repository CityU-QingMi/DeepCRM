    public CommentValidationManager() {
        
        // instantiate the validators that are configured
        try {
            String vals = WebloggerConfig.getProperty("comment.validator.classnames");
            String[] valsarray = Utilities.stringToStringArray(vals, ",");
            for (String arrayVal : valsarray) {
                try {
                    Class valClass = Class.forName(arrayVal);
                    CommentValidator val = (CommentValidator) valClass.newInstance();
                    validators.add(val);
                    log.info("Configured CommentValidator: " + val.getName() + " / " + valClass.getName());
                } catch (ClassNotFoundException cnfe) {
                    log.warn("Error finding comment validator: " + arrayVal);
                } catch (InstantiationException ie) {
                    log.warn("Error insantiating comment validator: " + arrayVal);
                } catch (IllegalAccessException iae) {
                    log.warn("Error accessing comment validator: " + arrayVal);
                }
            }
                        
        } catch (Exception e) {
            log.error("Error instantiating comment validators");
        }
        log.info("Configured " + validators.size() + " CommentValidators");
    }
