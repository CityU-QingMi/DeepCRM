    public String displayContent(String readMoreLink) {
        
        String displayContent = null;
        
        if(readMoreLink == null || readMoreLink.trim().length() < 1 || 
                "nil".equals(readMoreLink)) {
            
            // no readMore link means permalink, so prefer text over summary
            if(StringUtils.isNotEmpty(this.getText())) {
                displayContent = this.getTransformedText();
            } else {
                displayContent = this.getTransformedSummary();
            }
        } else {
            // not a permalink, so prefer summary over text
            // include a "read more" link if needed
            if(StringUtils.isNotEmpty(this.getSummary())) {
                displayContent = this.getTransformedSummary();
                if(StringUtils.isNotEmpty(this.getText())) {
                    // add read more
                    List<String> args = new ArrayList<String>();
                    args.add(readMoreLink);
                    
                    // TODO: we need a more appropriate way to get the view locale here
                    String readMore = I18nMessages.getMessages(getWebsite().getLocaleInstance()).getString("macro.weblog.readMoreLink", args);
                    
                    displayContent += readMore;
                }
            } else {
                displayContent = this.getTransformedText();
            }
        }
        
        return HTMLSanitizer.conditionallySanitize(displayContent);
    }
