    public String getStringProperty(String name, String def) {
        String val = props.getProperty(name, def);
        if (val == null) {
            return def;
        }
        
        val = val.trim();
        
        return (val.length() == 0) ? def : val;
    }
