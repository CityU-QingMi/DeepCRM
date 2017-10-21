    public static Map<String, String> createParameterMap(String[] params) {
        Map<String, String> map = new HashMap<>();
        int subtract = params.length % 2;
        if (subtract != 0) {
            throw new ConfigurationException(
                    "'params' is a string array "
                            + "and they must be in a key value pair configuration. It looks like you"
                            + " have specified an odd number of parameters and there should only be an even number."
                            + " (e.g. params = {\"key\", \"value\"})");
        }

        for (int i = 0; i < params.length; i = i + 2) {
            String key = params[i];
            String value = params[i + 1];
            map.put(key, value);
        }

        return map;
    }
