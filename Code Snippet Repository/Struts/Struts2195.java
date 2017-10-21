        public Collection getActionMessages() {
            if (justNullElement) {
                return Arrays.asList(null);
            } else if (canHaveActionMessage) {
                List messages = new ArrayList();
                messages.add("action message number 1");
                messages.add("action message number 2");
                messages.add("action message number 3");
                return messages;
            }
            else {
                return Collections.EMPTY_LIST;
            }
        }
