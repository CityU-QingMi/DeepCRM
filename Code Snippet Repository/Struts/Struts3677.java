    public void prepare() {
        if (writer == null) {
            try {
                writer = new FileWriter(output + "/out.dot");
            } catch (IOException e) {
                throw new StrutsException(e);
            }
        }

        StrutsConfigRetriever.setConfiguration(configDir, views.split("[,]+"));
        DOTRenderer renderer = new DOTRenderer(writer);
        renderer.render(namespace);
    }
