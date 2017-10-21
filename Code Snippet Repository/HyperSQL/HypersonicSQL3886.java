        public void actionPerformed(ActionEvent actionevent) {

            schemaFilter = actionevent.getActionCommand();

            if (schemaFilter.equals("*")) {
                schemaFilter = null;
            }

            refreshTree();
        }
