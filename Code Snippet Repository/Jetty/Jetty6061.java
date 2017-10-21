    public JsrExtension(ExtensionConfig cfg)
    {
        this.name = cfg.getName();
        if (cfg.getParameters() != null)
        {
            for (Map.Entry<String, String> entry : cfg.getParameters().entrySet())
            {
                parameters.add(new JsrParameter(entry.getKey(),entry.getValue()));
            }
        }
    }
