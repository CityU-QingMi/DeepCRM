    private List<String> getAcceptedContentTypeRange() throws WebloggerException {
        List<String> accepts = new ArrayList<String>();
        Weblogger roller = WebloggerFactory.getWeblogger();
        Map config = roller.getPropertiesManager().getProperties();        
        String allows = ((RuntimeConfigProperty)config.get("uploads.types.allowed")).getValue();
        String[] rules = StringUtils.split(StringUtils.deleteWhitespace(allows), ",");
        if (rules != null) {
            for (String rule : rules) {
                if (rule.indexOf('/') == -1) {
                    continue;
                }
                accepts.add(rule);
            }
        }
        return accepts;             
    }      
