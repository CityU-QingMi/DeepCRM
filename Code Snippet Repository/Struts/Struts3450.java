    private void addAditionalConfigurers(List<Configurer> configurers) {
        AnnotationsConfigurer annotationsConfigurer = new AnnotationsConfigurer();
        configurers.add(annotationsConfigurer);

        if (validateJPAAnnotations) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Adding support for JPA annotations validations in OVal");
            }
            configurers.add(new JPAAnnotationsConfigurer());
        }
    }
