    protected List<InterpolationPostProcessor> createPostProcessors( final Model model, final File projectDir,
                                                                     final ProjectBuilderConfiguration config )
    {
        return Collections.singletonList(
            (InterpolationPostProcessor) new PathTranslatingPostProcessor(
                PROJECT_PREFIXES,
                TRANSLATED_PATH_EXPRESSIONS,
                projectDir,
                pathTranslator ) );

    }
