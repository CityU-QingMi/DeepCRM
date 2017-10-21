        public Map<String, List<String>> getFieldErrors() {
            if (haveFieldErrors) {
                List err1 = new ArrayList();
                err1.add("field error message number 1");
                List err2 = new ArrayList();
                err2.add("field error message number 2");
                List err3 = new ArrayList();
                err3.add("field error message number 3");
                Map fieldErrors = new LinkedHashMap();
                fieldErrors.put("field1", err1);
                fieldErrors.put("field2", err2);
                fieldErrors.put("field3", err3);
                return fieldErrors;
            }
            else if (returnNullForFieldErrors) {
                return null;
            }
            else {
                return Collections.emptyMap();
            }
        }
