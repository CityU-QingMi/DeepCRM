    public void generate() throws IOException {
        Map<String, Object> params = context.getParameters();
        Map<String, List<String>> errors = (Map<String, List<String>>) findValue("fieldErrors");
        List<String> fieldErrorFieldNames = (List<String>) params.get("errorFieldNames");

        if (fieldErrorFieldNames != null && !fieldErrorFieldNames.isEmpty() && errors != null && !errors.isEmpty()) {
            startUL(params);

            //iterate over field error names
            for (String fieldErrorFieldName : fieldErrorFieldNames) {
                List<String> fieldErrors = errors.get(fieldErrorFieldName);
                if (fieldErrors != null) {
                    for (String fieldError : fieldErrors) {
                        writeError(params, fieldError);
                    }
                }
            }

            endUL();
        } else if (errors != null && !errors.isEmpty()) {
            startUL(params);

            for (Map.Entry<String, List<String>> errorEntry : errors.entrySet()) {
                for (String fieldError : errorEntry.getValue()) {
                    writeError(params, fieldError);
                }
            }

            endUL();
        }
    }
