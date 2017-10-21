        public Collection getActionErrors() {
             if (justNullElement) {
                return Arrays.asList(null);
            } else if (yesActionErrors) {
                List errors = new ArrayList();
                errors.add("action error number 1");
                errors.add("action error number 2");
                errors.add("action error number 3");
                return errors;
            }
            else {
                return Collections.EMPTY_LIST;
            }
        }
