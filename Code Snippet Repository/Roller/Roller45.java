    public Object getVariable(String name) {
        if (name == null) {
            throw new IllegalArgumentException("No variable with null key name.");
        }
        if (name.length() == 0) {
            throw new IllegalArgumentException("No variable with blank key name. [length=0]");
        }
        try {
            if ("out".equals(name)) {
                return printWriter;
            }
            if ("html".equals(name)) {
                if (html == null) {
                    html = new MarkupBuilder(printWriter);
                }
                return html;
            }
        } catch (Exception e) {
            String message = "Failed to get writer or output stream from response.";
            log.error(message, e);
            throw new RuntimeException(message, e);
        }

        return binding.getVariable(name);
    }    
