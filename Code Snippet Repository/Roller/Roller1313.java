    public WeblogTemplate getTemplateByLink(Weblog weblog, String templateLink)
    throws WebloggerException {
        
        if (weblog == null) {
            throw new WebloggerException("userName is null");
        }

        if (templateLink == null) {
            throw new WebloggerException("templateLink is null");
        }

        TypedQuery<WeblogTemplate> query = strategy.getNamedQuery("WeblogTemplate.getByWeblog&Link",
                WeblogTemplate.class);
        query.setParameter(1, weblog);
        query.setParameter(2, templateLink);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
