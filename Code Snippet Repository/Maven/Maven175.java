        public InterpolateObjectAction( Object target, List<ValueSource> valueSources,
                                        List<InterpolationPostProcessor> postProcessors, boolean debugEnabled,
                                        StringSearchModelInterpolator modelInterpolator, Logger logger )
        {
            this.valueSources = valueSources;
            this.postProcessors = postProcessors;
            this.debugEnabled = debugEnabled;

            this.interpolationTargets = new LinkedList<>();
            interpolationTargets.add( target );

            this.modelInterpolator = modelInterpolator;
            this.logger = logger;
        }
