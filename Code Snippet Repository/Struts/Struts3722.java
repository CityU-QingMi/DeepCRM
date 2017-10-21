    protected void registerTilesBeanModel(ActionContext ctx) {
        ServletContext servletContext = ServletActionContext.getServletContext();
        Configuration configuration = ctx.getInstance(FreemarkerManager.class).getConfiguration(servletContext);

        StrutsBeanWrapper wrapper = (StrutsBeanWrapper) ctx.getInstance(FreemarkerManager.class).getWrapper();

        LOG.trace("Adding support for Tiles tags, please remember to register {} in web.xml!", JspSupportServlet.class.getName());

        BeanModel tilesBeanModel = new BeanModel(new TilesFMModelRepository(), wrapper);
        configuration.setSharedVariable("tiles", tilesBeanModel);
    }
