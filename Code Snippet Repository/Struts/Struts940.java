    protected void loadSettings(ServletContext servletContext) {
        try (InputStream in = fileManager.loadFile(ClassLoaderUtil.getResource("freemarker.properties", getClass()))){
            if (in != null) {
                Properties p = new Properties();
                p.load(in);

                for (Object o : p.keySet()) {
                    String name = (String) o;
                    String value = (String) p.get(name);

                    if (name == null) {
                        throw new IOException(
                                "init-param without param-name.  Maybe the freemarker.properties is not well-formed?");
                    }
                    if (value == null) {
                        throw new IOException(
                                "init-param without param-value.  Maybe the freemarker.properties is not well-formed?");
                    }
                    addSetting(name, value);
                }
            }
        } catch (IOException e) {
            LOG.error("Error while loading freemarker settings from /freemarker.properties", e);
        } catch (TemplateException e) {
            LOG.error("Error while loading freemarker settings from /freemarker.properties", e);
        }
    }
