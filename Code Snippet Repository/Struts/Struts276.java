    protected String getParameterLogMap(HttpParameters parameters) {
        if (parameters == null) {
            return "NONE";
        }

        StringBuilder logEntry = new StringBuilder();
        for (String name : parameters.keySet()) {
            logEntry.append(String.valueOf(name));
            logEntry.append(" => ");
            logEntry.append(parameters.get(name).getValue());
            logEntry.append(" ");
        }

        return logEntry.toString();
    }
