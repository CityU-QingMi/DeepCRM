    protected TilesContainer getCurrentContainer(javax.portlet.PortletRequest request, PortletContext context) {

        TilesContainer container = (TilesContainer) request.getAttribute(TilesAccess.CURRENT_CONTAINER_ATTRIBUTE_NAME);

        if (container == null) {
            container = getContainer(context);
            request.setAttribute(TilesAccess.CURRENT_CONTAINER_ATTRIBUTE_NAME, container);
        }

        return container;
    }
