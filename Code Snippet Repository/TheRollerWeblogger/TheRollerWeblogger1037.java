    public void setVariable(String name, Object value) {
        if (name == null) {
            throw new IllegalArgumentException("Can't bind variable to null key.");
        }
        if (name.length() == 0) {
            throw new IllegalArgumentException("Can't bind variable to blank key name. [length=0]");
        }
        if ("out".equals(name)) {
            throw new IllegalArgumentException("Can't bind variable to key named '" + name + "'.");
        }
        if ("html".equals(name)) {
            throw new IllegalArgumentException("Can't bind variable to key named '" + name + "'."); 
        }
        binding.setVariable(name, value);
    }
