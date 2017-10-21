        public void store() {

            if (prefsFile == null) {

                // Can't persist Applet settings.
                return;
            }

            Properties props = new Properties();

            // Boolean.toString(boolean) was new with Java 1.4, so don't use that.
            props.setProperty("autoRefresh", (autoRefresh ? tString
                                                          : fString));
            props.setProperty("showRowCounts", (showRowCounts ? tString
                                                              : fString));
            props.setProperty("showSysTables", (showSysTables ? tString
                                                              : fString));
            props.setProperty("showSchemas", (showSchemas ? tString
                                                          : fString));
            props.setProperty("resultGrid", (resultGrid ? tString
                                                        : fString));
            props.setProperty("laf", laf);
            props.setProperty("showTooltips", (showTooltips ? tString
                                                            : fString));

            try {
                FileOutputStream fos = new FileOutputStream(prefsFile);

                props.store(fos, "DatabaseManagerSwing user preferences");
                fos.flush();
                fos.close();
            } catch (IOException ioe) {
                throw new RuntimeException(
                    "Failed to prepare preferences file '" + prefsFile
                    + "':  " + ioe.getMessage());
            }
        }
