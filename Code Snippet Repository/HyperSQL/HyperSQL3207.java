    private void updateSchemaList() {

        ButtonGroup group  = new ButtonGroup();
        ArrayList   list   = new ArrayList();
        ResultSet   result = null;

        try {
            result = dMeta.getSchemas();

            if (result == null) {
                throw new SQLException("Failed to get metadata from database");
            }

            while (result.next()) {
                list.add(result.getString(1));
            }
        } catch (SQLException se) {
            CommonSwing.errorMessage(se);
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException se) {}
            }
        }

        mnuSchemas.removeAll();
        rbAllSchemas.setSelected(schemaFilter == null);
        group.add(rbAllSchemas);
        mnuSchemas.add(rbAllSchemas);

        String               s;
        JRadioButtonMenuItem radioButton;

        for (int i = 0; i < list.size(); i++) {
            s           = (String) list.get(i);
            radioButton = new JRadioButtonMenuItem(s);

            group.add(radioButton);
            mnuSchemas.add(radioButton);
            radioButton.setSelected(schemaFilter != null
                                    && schemaFilter.equals(s));
            radioButton.addActionListener(schemaListListener);
            radioButton.setEnabled(list.size() > 1);
        }

        mnuSchemas.addSeparator();
        mnuSchemas.add(mitemUpdateSchemas);
    }
