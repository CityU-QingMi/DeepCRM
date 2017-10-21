    private void populateParameters(Map<String,String> params,Template template, Instance instance)
    {
        try
        {
            params.put(OVERLAYS_DIR,_scanDir.getCanonicalPath());
            if (template!=null)
            {
                params.put(OVERLAY_TEMPLATE,template.getName());
                params.put(OVERLAY_TEMPLATE_NAME,template.getTemplateName());
                params.put(OVERLAY_TEMPLATE_CLASSIFIER,template.getClassifier());
                params.put(OVERLAY_WEBAPP,template.getWebapp()==null?null:template.getWebapp().getName());
            }
            if (_node!=null)
                params.put(OVERLAY_NODE,_node.getName());
            if (instance!=null)
            {
                params.put(OVERLAY_INSTANCE,instance.getName());
                params.put(OVERLAY_INSTANCE_CLASSIFIER,instance.getClassifier());
            }
            if (getConfigurationManager()!=null)
                params.putAll(getConfigurationManager().getProperties());
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }
