    public void actionPerformed(ActionEvent ev) {

        String s = ev.getActionCommand();

        if (s.equals("ConnectOk") || (ev.getSource() instanceof TextField)) {
            try {
                if (mURL.getText().indexOf('\u00AB') >= 0) {
                    throw new Exception("please specify db path");
                }

                mConnection = createConnection(mDriver.getText(),
                                               mURL.getText(),
                                               mUser.getText(),
                                               mPassword.getText());

                if (mName.getText() != null
                        && mName.getText().trim().length() != 0) {
                    ConnectionSetting newSetting =
                        new ConnectionSetting(mName.getText(),
                                              mDriver.getText(),
                                              mURL.getText(), mUser.getText(),
                                              mPassword.getText());

                    ConnectionDialogCommon.addToRecentConnectionSettings(
                        settings, newSetting);
                }

                dispose();
            } catch (java.io.IOException ioe) {
                dispose();
            } catch (Exception e) {
                e.printStackTrace();
                mError.setText(e.toString());
            }
        } else if (s.equals("ConnectCancel")) {
            dispose();
        }
    }
