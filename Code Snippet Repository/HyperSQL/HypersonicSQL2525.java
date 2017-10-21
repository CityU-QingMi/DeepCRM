    public void addProperties(Properties props) {

        if (props == null) {
            return;
        }

        Enumeration keys = props.propertyNames();

        while (keys.hasMoreElements()) {
            String key   = (String) keys.nextElement();
            String value = props.getProperty(key);

            this.stringProps.put(key, value);
        }
    }
